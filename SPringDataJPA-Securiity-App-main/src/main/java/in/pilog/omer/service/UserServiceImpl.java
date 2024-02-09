package in.pilog.omer.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.pilog.omer.repository.IUserDetailsRepo;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDetailsRepo repo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("UserServiceImpl.loadUserByUsername()");

		Optional<in.pilog.omer.model.UserDetails> optional = repo.findByUname(username);
		if (optional.isEmpty()) {
			throw new IllegalArgumentException("user not found");
		} else {

			in.pilog.omer.model.UserDetails details = optional.get();

			User user = new User(details.getUname(), details.getPwd(), details.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet()));
			return user;
		}
	}

	@Override
	public String regsiter(in.pilog.omer.model.UserDetails details) {
		System.out.println("UserServiceImpl.regsiter()");

		details.setPwd(encoder.encode(details.getPwd()));

		return repo.save(details).getUid() + " UserId is registered";
	}

}
