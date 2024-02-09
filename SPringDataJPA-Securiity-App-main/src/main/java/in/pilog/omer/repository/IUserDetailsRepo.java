package in.pilog.omer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import in.pilog.omer.model.UserDetails;

public interface IUserDetailsRepo extends CrudRepository<UserDetails, Integer> {
	public Optional<UserDetails> findByUname(String uname);
}
