package com.fancye.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fancye.springmvc.dao.UserDAO;
import com.fancye.springmvc.model.User;
import com.fancye.springmvc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public int insertUser(User user) {
		return userDAO.insertUser(user);
	}

}
