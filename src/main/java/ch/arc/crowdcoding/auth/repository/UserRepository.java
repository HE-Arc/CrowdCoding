package ch.arc.crowdcoding.auth.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ch.arc.crowdcoding.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
