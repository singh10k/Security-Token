package tms.cloud.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tms.cloud.com.model.LogImgDetails;
import tms.cloud.com.model.UserDetails;

public interface UserDetailsRepo extends CrudRepository<UserDetails, Integer> {
	public Optional<UserDetails> findByUname(String uname);
}
