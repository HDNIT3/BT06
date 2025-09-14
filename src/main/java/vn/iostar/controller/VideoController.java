package vn.iostar.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

        String filePath = uploadDir + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());
        
        videoSer.save(new Video(null,title,filePath,cateSer.findById(id)));

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
	
	@GetMapping("/path/{filePath}")
    @ResponseBody
    public ResponseEntity<Resource> getVideo(@PathVariable String filePath) throws MalformedURLException {
		Path path = Paths.get(filePath);
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .body(resource);
    }
}
