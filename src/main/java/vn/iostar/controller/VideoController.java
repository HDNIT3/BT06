package vn.iostar.controller;

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import vn.iostar.entity.Video;
import vn.iostar.service.CategoryService;
import vn.iostar.service.VideoService;

@Controller
@RequestMapping("admin/video")
public class VideoController {
	
	@Autowired
	VideoService videoSer;
	
	@Autowired
	CategoryService cateSer;
	
	private final String uploadDir = "uploads/videos/";
	
	@PostMapping("/add")
    public String uploadVideo(
    		@RequestParam("id") Long id,
    		@RequestParam("file") MultipartFile file,
    		@RequestParam("title") String title,
    		Model model
    		) throws IOException {
        if (file.isEmpty()) {
            return "redirect:video/home";
        }

        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir).resolve(filename);
        Files.write(path, file.getBytes());
        
        videoSer.save(new Video(null,title,filename,cateSer.findById(id)));

        return "redirect:home?id=" + id;
    }
	
	@GetMapping("/home")
	public String getVideo(@RequestParam("id") Long id,Model model) {
		model.addAttribute("listVideo",cateSer.findById(id).getVideo());
		model.addAttribute("id",id);
		return "admin/video/home";
	}
	
	@GetMapping("/add")
	public String add(@RequestParam("id") Long id,Model model) {
		model.addAttribute("id",id);
		return "admin/video/add";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id,@RequestParam("idvideo") Long idvideo,Model model) {
		videoSer.deleteById(idvideo);
		return "redirect:home?id=" + id;
	}
	
	 // --- Stream video ---
    @GetMapping("/path/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getVideo(@PathVariable String filename,
                                             @RequestHeader(value = "Range", required = false) String rangeHeader)
            throws IOException {
        Path path = Paths.get(uploadDir).resolve(filename).normalize();
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        long fileLength = resource.contentLength();

 
        if (rangeHeader == null) {
            return ResponseEntity.ok()
                    .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                    .contentLength(fileLength)
                    .body(resource);
        }


        long rangeStart = 0;
        long rangeEnd = fileLength - 1;

        String[] ranges = rangeHeader.replace("bytes=", "").split("-");
        try {
            if (ranges.length > 0 && !ranges[0].isEmpty()) {
                rangeStart = Long.parseLong(ranges[0]);
            }
            if (ranges.length > 1 && !ranges[1].isEmpty()) {
                rangeEnd = Long.parseLong(ranges[1]);
            }
        } catch (NumberFormatException ignored) {}

        if (rangeEnd > fileLength - 1) {
            rangeEnd = fileLength - 1;
        }

        long contentLength = rangeEnd - rangeStart + 1;
        InputStream inputStream = resource.getInputStream();
        inputStream.skip(rangeStart);

        InputStreamResource inputStreamResource = new InputStreamResource(new LimitedInputStream(inputStream, contentLength));

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .header(HttpHeaders.CONTENT_RANGE, "bytes " + rangeStart + "-" + rangeEnd + "/" + fileLength)
                .contentLength(contentLength)
                .body(inputStreamResource);
    }

    static class LimitedInputStream extends FilterInputStream {
        private long remaining;
        protected LimitedInputStream(InputStream in, long limit) {
            super(in);
            this.remaining = limit;
        }
        @Override
        public int read() throws IOException {
            if (remaining <= 0) return -1;
            int result = super.read();
            if (result != -1) remaining--;
            return result;
        }
        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            if (remaining <= 0) return -1;
            len = (int) Math.min(len, remaining);
            int result = super.read(b, off, len);
            if (result != -1) remaining -= result;
            return result;
        }
    }
}
