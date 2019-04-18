package ch.arc.crowdcoding.service;

import ch.arc.crowdcoding.model.User;

public interface UserService {
	public User findUserByName(String name);
	public void saveUser(User user);
	public Iterable<User> findAll();
}
