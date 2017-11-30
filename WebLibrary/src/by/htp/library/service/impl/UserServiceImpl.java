package by.htp.library.service.impl;

import by.htp.library.bean.User;
import by.htp.library.dao.UserDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dao.factory.DaoFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class UserServiceImpl implements UserService {
	private static final String MESSAGE_ERROR_LOGIN = "Incorect login";
	private static final String MESSAGE_ERROR_PASSWORD = "Incorect password";
	private static final String MESSAGE_ERROR_LOGIN_OR_PASSWORD = "Incorect login or password";
	private static final String MESSAGE_ERROR_NAME = "Incorect name";
	private static final String MESSAGE_ERROR_SURNAME = "Incorect surname";
	private static final String MESSAGE_ERROR_DATA_USER = "Incorect data user";
	
	/** Check user by login and password to the database
	 * 
	 * @param login
	 * @param password
	 * @return returns user
	 * @throws ServiceException if you cannot to check user by login and password 
	 */
	@Override
	public User signIn(String login, String password) throws ServiceException {

		User user = null;

		if (login == null || login.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_LOGIN);
		}

		if (password == null || password.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_PASSWORD);
		}

		try {
			DaoFactory factory = DaoFactory.getInstance();
			UserDao userDao = factory.getUserDao();
			user = userDao.signIn(login, password);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_LOGIN_OR_PASSWORD ,e);
		}

		return user;

	}

	
	/** Add the user to the database
	 * 
	 * @param user
	 * @throws ServiceException if you cannot add user
	 */
	@Override
	public void registration(User user) throws ServiceException {

		if (user.getName() == null || user.getName().isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_NAME);
		}

		if (user.getSurname() == null || user.getSurname().isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_SURNAME);
		}

		if (user.getLogin() == null || user.getLogin().isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_LOGIN);
		}

		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_PASSWORD);
		}

		try {
			DaoFactory factory = DaoFactory.getInstance();
			UserDao userDao = factory.getUserDao();
			userDao.registration(user);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_DATA_USER, e);
		}

	}

}
