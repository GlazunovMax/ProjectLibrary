package by.htp.library.service.impl;

import by.htp.library.bean.User;
import by.htp.library.dao.UserDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dao.factory.DaoFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	@Override
	public User signIn(String login, String password) throws ServiceException {

		User user = null;

		if (login == null || login.isEmpty()) {/// pегуляными выажениями
			throw new ServiceException("Incorect login");
		}

		if (password == null || password.isEmpty()) {
			throw new ServiceException("Incorect password");
		}

		try {
			DaoFactory factory = DaoFactory.getInstance();
			UserDao userDao = factory.getUserDao();
			user = userDao.signIn(login, password);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

		return user;

	}

	@Override
	public void registration(User user) throws ServiceException {

		if (user.getName() == null || user.getName().isEmpty()) {/// ????
			throw new ServiceException("Incorect name");
		}

		if (user.getSurname() == null || user.getSurname().isEmpty()) {/// ????
			throw new ServiceException("Incorect surname");
		}

		if (user.getLogin() == null || user.getLogin().isEmpty()) {/// ????
			throw new ServiceException("Incorect login");
		}

		if (user.getPassword() == null || user.getPassword().isEmpty()) {/// ????
			throw new ServiceException("Incorect password");
		}

		try {
			DaoFactory factory = DaoFactory.getInstance();
			UserDao userDao = factory.getUserDao();
			userDao.registration(user);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

}
