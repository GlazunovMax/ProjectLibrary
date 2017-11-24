package by.htp.library.service.impl;

import java.util.List;
import by.htp.library.bean.PublishedBy;
import by.htp.library.dao.PublishedByDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dao.factory.DaoFactory;
import by.htp.library.service.PublishedByService;
import by.htp.library.service.exception.ServiceException;

public class PublishedByServiceImpl implements PublishedByService{
	
	private static final String MESSAGE_ERROR_GET_ALL_PUBLISHED = "Can`t get all published by";
	private static final String MESSAGE_ERROR_EMPTY_PUBLISHED = "The search has not given any results";
	private static final String MESSAGE_ERROR_ADD_PUBLISHED = "Cannot add published by";
	
	@Override
	public void addPublishedBy(PublishedBy publishedBy) throws ServiceException {
		if (publishedBy.getPublishedByTitle() == null || publishedBy.getPublishedByTitle().isEmpty()) {/// ????
			throw new ServiceException(MESSAGE_ERROR_ADD_PUBLISHED);
		}
		
		DaoFactory factory = DaoFactory.getInstance();
		PublishedByDao publishedByDao = factory.getPublishedByDao();
		try {
			publishedByDao.add(publishedBy);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_ADD_PUBLISHED, e);
		}
	}


	@Override
	public List<PublishedBy> getAllPublishedBy() throws ServiceException {

			List<PublishedBy> publishedByList = null;
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			PublishedByDao publishedByDao = factory.getPublishedByDao();
			publishedByList = publishedByDao.getAllPublishedBy();
		} catch (DaoException e) {
				throw new ServiceException(MESSAGE_ERROR_GET_ALL_PUBLISHED, e);
		}
		
		if (publishedByList == null || publishedByList.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_EMPTY_PUBLISHED);
		}

		return publishedByList;
	}

	

}
