package ch.arc.crowdcoding.service;

import ch.arc.crowdcoding.model.User;

public interface UserService {
	public User findUserByName(String email);
	public void saveUser(User user);
	public Iterable<User> findAll();
}
