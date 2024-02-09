package in.pilog.omer.service;

import in.pilog.omer.model.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService{
	public String regsiter(UserDetails details);
}
