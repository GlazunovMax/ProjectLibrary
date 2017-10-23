package by.htp.library.dao;

import by.htp.library.bean.User;
import by.htp.library.dao.exception.DaoException;

public interface UserDao {
	
	User signIn(String login, String password) throws DaoException;
	
	void registration(User user) throws DaoException;
	

	
}
