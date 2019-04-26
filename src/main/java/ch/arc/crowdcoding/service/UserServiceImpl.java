package ch.arc.crowdcoding.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.arc.crowdcoding.model.Role;
import ch.arc.crowdcoding.model.User;
import ch.arc.crowdcoding.repository.RoleRepository;
import ch.arc.crowdcoding.repository.UserRepository;


@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public User findUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public void saveUser(User user) {
		user.setActive(1);
		 user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        Role userRole = roleRepository.findByRole("USER");
	        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findUserById(Integer id) {
		Optional<User> oUser = userRepository.findById(id);
		if(oUser.isPresent())
			return oUser.get();
		else
			return null;
	}

	@Override
	public boolean setAdmin(String username) {
		User user = userRepository.findByName(username);
		if(user == null)
			return false;
		
		Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
        return true;
	}
	
	@Override
	public boolean revokeAdmin(Integer id) {
		Optional<User> oUser = userRepository.findById(id);
		if(!oUser.isPresent())
			return false;
		
		User user = oUser.get();
		
		Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
        return true;
	}

	@Override
	public Iterable<User> findAllAdmin() {
		Iterable<User> admins = userRepository.findByRoles(roleRepository.findByRole("ADMIN"));
		return admins;
	}



}
