package by.htp.library.service;

import by.htp.library.bean.User;
import by.htp.library.service.exception.ServiceException;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public interface UserService {

	/** Check user by login and password to the database
	 * 
	 * @param login
	 * @param password
	 * @return returns user
	 * @throws ServiceException if you cannot to check user by login and password 
	 */
	User signIn(String login, String password) throws ServiceException;
	
	
	/** Add the user to the database
	 * 
	 * @param user
	 * @throws ServiceException if you cannot add user
	 */
	void registration(User user) throws ServiceException;
}
