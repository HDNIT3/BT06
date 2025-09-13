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
import vn.iostar.entity.Category;
import vn.iostar.entity.User;
import vn.iostar.service.CategoryService;

@Controller
@RequestMapping("admin/category")
public class CateController {
	@Autowired
	CategoryService cateSer;
	
	@GetMapping("add")
	public String add(Model model) {
		return "admin/category/add";
	}
	@GetMapping("delete")
	public String delete(@RequestParam("id") Long id) {
		cateSer.deleteById(id);
		return "redirect:/admin/login";
	}
	
	@PostMapping("add")
	public String addRes(@RequestParam("category") String category,HttpServletRequest req) {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("account");
		if (u == null) {
			return "redirect:/admin/login";
		}
		cateSer.save(new Category(null,category,u.getId()));
		return "redirect:/admin/home";
	}
}
