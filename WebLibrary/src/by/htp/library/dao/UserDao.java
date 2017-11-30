package by.htp.library.dao;

import by.htp.library.bean.User;
import by.htp.library.dao.exception.DaoException;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public interface UserDao {
	
	/** Check user by login and password to the database
	 * 
	 * @param login
	 * @param password
	 * @return returns user
	 * @throws DaoException if you cannot to check user by login and password 
	 */
	User signIn(String login, String password) throws DaoException;
	
	/** Add the user to the database
	 * 
	 * @param user
	 * @throws DaoException if you cannot add user
	 */
	void registration(User user) throws DaoException;
	

	
}
