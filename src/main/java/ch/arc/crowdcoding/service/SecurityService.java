package ch.arc.crowdcoding.service;

import ch.arc.crowdcoding.model.User;

public interface SecurityService {
	String findLoggedInUsername();

    void autoLogin(String username, String password);
}
