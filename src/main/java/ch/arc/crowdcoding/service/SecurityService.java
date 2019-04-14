package ch.arc.crowdcoding.service;

public interface SecurityService {
	String findLoggedInUsername();

    void autoLogin(String username, String password);
}
