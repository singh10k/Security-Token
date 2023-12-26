package tms.cloud.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/TMS")
public class tmsContoller {
	@RequestMapping(value = "/home", method = { RequestMethod.GET, RequestMethod.POST })
	public String tmsHome() {
		return "tms";
	}
}
