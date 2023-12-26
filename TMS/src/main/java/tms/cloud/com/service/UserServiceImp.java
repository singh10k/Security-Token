package tms.cloud.com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import tms.cloud.com.model.LogImgDetails;
import tms.cloud.com.repository.UserDetailsRepo;
import tms.cloud.com.repository.UserLoginAImgRepo;

@Service("userService")
public class UserServiceImp implements UserService {

	@Autowired 
	private UserDetailsRepo repo;
	
	@Autowired 
	private UserLoginAImgRepo imgRepo;
	
	@Autowired 
	private BCryptPasswordEncoder encoder;
	@Override
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("UserServiceImpl.loadUserByUsername()");

		Optional<tms.cloud.com.model.UserDetails> optional = repo.findByUname(username);
		if (optional.isEmpty()) {
			throw new IllegalArgumentException("user not found");
		} else {

			tms.cloud.com.model.UserDetails details = optional.get();

			User user = new User(details.getUname(), details.getPwd(), details.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet()));
			return user;
		}
	}

	@Override
	public String regsiter(tms.cloud.com.model.UserDetails details) {
		System.out.println("UserServiceImpl.regsiter()");

		details.setPwd(encoder.encode(details.getPwd()));

		return repo.save(details).getUid() + " UserId is registered";
	}
	
	@Override 
	public List<LogImgDetails> getOrgAllImg(){
		return (List<LogImgDetails>) imgRepo.findAll();
	}


}
