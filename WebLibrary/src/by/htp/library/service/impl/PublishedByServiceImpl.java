package by.htp.library.service.impl;

import java.util.List;
import by.htp.library.bean.PublishedBy;
import by.htp.library.dao.PublishedByDao;
import by.htp.library.dao.exception.DaoException;
import by.htp.library.dao.factory.DaoFactory;
import by.htp.library.service.PublishedByService;
import by.htp.library.service.exception.ServiceException;

public class PublishedByServiceImpl implements PublishedByService{

	@Override
	public void addPublishedBy(PublishedBy publishedBy) throws ServiceException {
		if (publishedBy.getPublishedByTitle() == null || publishedBy.getPublishedByTitle().isEmpty()) {/// ????
			throw new ServiceException("Cannot add published by! ");
		}
		
		DaoFactory factory = DaoFactory.getInstance();
		PublishedByDao publishedByDao = factory.getPublishedByDao();
		try {
			publishedByDao.add(publishedBy);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public void updatePublishedBy(PublishedBy publishedBy) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePublishedBy(PublishedBy publishedBy) throws ServiceException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<PublishedBy> getAllPublishedBy() throws ServiceException {

			List<PublishedBy> publishedByList = null;
		
		try {
			DaoFactory factory = DaoFactory.getInstance();
			PublishedByDao publishedByDao = factory.getPublishedByDao();
			publishedByList = publishedByDao.getAllPublishedBy();
		} catch (DaoException e) {
				throw new ServiceException(e);
		}
		
		if (publishedByList == null || publishedByList.isEmpty()) {/// ????
			throw new ServiceException("The search has not given any results");
		}

		return publishedByList;
	}

	

}
