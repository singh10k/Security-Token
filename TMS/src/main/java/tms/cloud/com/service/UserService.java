package tms.cloud.com.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import tms.cloud.com.model.LogImgDetails;
import tms.cloud.com.model.UserDetails;

public interface UserService extends UserDetailsService {
public String regsiter(UserDetails details);
public List<LogImgDetails> getOrgAllImg();
}
