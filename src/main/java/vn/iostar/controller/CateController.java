package vn.iostar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import vn.iostar.service.CategoryService;

@Controller
@RequestMapping("admin/category")
public class CateController {
	@Autowired
	CategoryService cateSer;
	
	@GetMapping("add")
	public String add(Model model , HttpServletRequest req) {
		return "admin/category/add";
	}
}
