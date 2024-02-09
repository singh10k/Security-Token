package tms.cloud.com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tms.cloud.com.model.LogImgDetails;
import tms.cloud.com.model.UserDetails;
import tms.cloud.com.service.UserService;

@Controller
@RequestMapping("/")
public class loginController {
	@Autowired
	UserService service;
	
	@RequestMapping("/loginPage")
	public String lognPage() {
//		List<LogImgDetails> allImgList=service.getOrgAllImg();
		return "loginPage";
	}
	@GetMapping("/register")
	public String showRegisterPage(@ModelAttribute("userInfo") UserDetails details) {
		System.out.println("HomeController.showRegisterPage()");
		return "user_register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("userInfo") UserDetails details, Map<String, Object> map) {
		System.out.println("HomeController.registerUser()");
		String resultMsg = service.regsiter(details);
		map.put("message", resultMsg);
		return "user_registered_success";
	}

	@GetMapping("/denied")
	public String accessDenied(Map<String, Object> map) {
		map.put("username", SecurityContextHolder.getContext().getAuthentication().getName());
		return "access_denied";
	}
}
