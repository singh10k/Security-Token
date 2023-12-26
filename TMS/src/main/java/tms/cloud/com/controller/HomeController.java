package tms.cloud.com.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tms.cloud.com.model.UserDetails;
import tms.cloud.com.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	UserService service;

	@RequestMapping("/home")
	public String homePage() {
		return "home";
	}

	@RequestMapping(value = "/showLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody StringBuilder getLoginSocialMedia(HttpServletRequest request) {
		StringBuilder str = new StringBuilder();
		try {
			str.append("<form action=\"/login\" method=\"POST\">")
					.append("<table border=\"0\" bgcolor=\"cyan\" align=\"center\">")
					.append("<tr><td> username :: </td><td> <input type=\"text\" name=\"username\" /></td></tr>")
					.append("<tr><td> password :: </td><td> <input type=\"password\" name=\"password\" /> </td></tr>")
					.append("<tr><td><input type=\"submit\" value=\"Login\"></td><td><input type=\"reset\" value=\"cancel\"></td></tr>")
					.append("</table>")
					.append("<span style=\"color:red;text-align:center\" th:if=\"${param.error}\">Invalid Login detials (authentication failed)</span>")
					.append("<span style=\"color:red;text-align:center\" th:if=\"${param.logout}\">User Logged succefully </span>")
					.append("</form>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}


}
