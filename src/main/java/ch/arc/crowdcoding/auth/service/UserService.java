package ch.arc.crowdcoding.auth.service;

import ch.arc.crowdcoding.auth.model.User;

public interface UserService {
	void save(User user);

    User findByUsername(String username);
}
