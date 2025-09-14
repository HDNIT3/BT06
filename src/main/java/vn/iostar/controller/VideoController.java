package vn.iostar.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("admin/video")
public class VideoController {
	private final String uploadDir = "uploads/videos/";
	
	@PostMapping("/upload")
    public String uploadVideo(
    		@RequestParam("id") String id,
    		@RequestParam("file") MultipartFile file,
    		@RequestParam("title") String title,
    		Model model
    		) throws IOException {
        if (file.isEmpty()) {
            return "video/add";
        }

        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String filePath = uploadDir + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());

        model.addAttribute("id",id);
        return "video/home";
    }
	
	@GetMapping("/home")
	public String getVideo() {
		
		return "video/home";
	}
}
