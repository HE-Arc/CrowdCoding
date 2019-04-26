package ch.arc.crowdcoding.service;

import ch.arc.crowdcoding.model.User;

public interface UserService {
	public User findUserByName(String name);
	public User findUserById(Integer id);
	public void saveUser(User user);
	public Iterable<User> findAll();
	
	public boolean setAdmin(String username);
	public boolean revokeAdmin(Integer id);
	public Iterable<User> findAllAdmin();
}
