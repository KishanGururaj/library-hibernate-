package com.wolken.wolkenapp.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.wolken.wolkenapp.LibraryException;
import com.wolken.wolkenapp.dao.LibraryDAO;
import com.wolken.wolkenapp.dao.LibraryDAOImpl;
import com.wolken.wolkenapp.dto.LibraryDTO;

public class LibraryServiceImpl implements LibraryService {
	Logger logger = Logger.getLogger("LibraryServiceImpl");

	LibraryDAO libraryDAO = new LibraryDAOImpl();

	@Override
	public boolean validateAndSave(LibraryDTO libraryDTO) {
		// TODO Auto-generated method stub
		try {
		logger.info("inside validate and save ");

		if (libraryDTO.getBookId() > 0) {
			if (libraryDTO.getBookName() != null) {
				if (libraryDTO.getPrice() > 0) {
					if (libraryDTO.getBookAuthor() != null) {
						
							logger.info("inside validate and save success");
							return libraryDAO.save(libraryDTO);
						
					} else {
						logger.error("Author name is not valid ");
						return false;
					}
				} else {
					logger.error("price is not valid");
					return false;
				}
			} else {
				logger.error("book name is not valid");
				return false;
			}
		} else {
			logger.error("book id is not valid");
			return false;
		}
	
		} catch (NullPointerException e) {
			logger.warn("null pointer exception inside validate and save ");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean validateAndUpdate(String name, String author) {
		// TODO Auto-generated method stub
		logger.info("inside validate and update");
		if (name != null) {
			if (author != null) {
				try {
					logger.info("inside null check is done in validate and update  ");
					return libraryDAO.updateByName(name, author);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			} else {
				logger.error("author varibale is null");
				return false;
			}
		} else {
			logger.error("name variable is null");
			return false;
		}
		return false;
	}

	@Override
	public boolean validateAndDelete(String name) {
		// TODO Auto-generated method stub
		logger.info(" inside validate and delete ");
		if (name != null) {
			try {
				logger.info("inside null condtion inside validate and update");
				return libraryDAO.deleteByName(name);
			} catch (LibraryException|NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			logger.error("name is null inside validate and update ");
		}
		return false;

	}

	@Override
	public List<LibraryDTO> getAll() {
		// TODO Auto-generated method stub
		return libraryDAO.getAll();
	}

}
