package ch.arc.crowdcoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.crowdcoding.model.Role;
import ch.arc.crowdcoding.model.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByName(String name);
	Iterable<User> findByRoles(Role role);
}