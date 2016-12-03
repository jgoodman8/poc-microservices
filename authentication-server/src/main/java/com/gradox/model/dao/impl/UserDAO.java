package com.gradox.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradox.model.dao.IUserDAO;
import com.gradox.model.repositories.UserRepository;
import com.gradox.model.vo.User;

@Service
public class UserDAO implements IUserDAO {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User register(User user) {
		return userRepository.save(user);
	}
}
