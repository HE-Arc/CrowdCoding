package ch.arc.crowdcoding.service;

import ch.arc.crowdcoding.model.User;

public interface SecurityService {
	String findLoggedInUsername();
	User findLoggedInUser();
	
    void autoLogin(String username, String password);
}
