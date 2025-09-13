package vn.iostar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.iostar.entity.User;
import vn.iostar.service.UserService;

@Controller
@RequestMapping("admin")
public class UserController {

	@Autowired
	private UserService userSer;

	@GetMapping("/login")
	public String loginPage(Model model,HttpServletRequest req) {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("account");
		if (u != null) {
			return "redirect:/admin/home";
		}
		return "web/login";
	}

	@PostMapping("/login")
	public String loginProcess(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (userSer.checklogin(username, password)) {
			session.setAttribute("account", userSer.findByUsername(username));
			return "redirect:/admin/home";
		} else {
			return "web/login";
		}
	}

	@GetMapping("/home")
	public String homePage(Model model) {
		return "admin/home";
	}

	@GetMapping("/logout")
	public String LoginPage(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/admin/login";
	}

}
