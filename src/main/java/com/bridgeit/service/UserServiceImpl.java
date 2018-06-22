package com.bridgeit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.dao.IUserDao;
import com.bridgeit.model.Login;
import com.bridgeit.model.User1;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Transactional
	@Override
	public int register(User1 user) {
		int status = 0;
		if(user!=null) {
			User1 usercheck = userDao.getByEmail(user.getEmail());
			if(usercheck==null) {
				status = userDao.insert(user);
			}else {
				status = 0;
			}
		}
		
		return status;
	}

	@Override
	public User1 login(Login login) {
		User1 user = null;
		System.out.println("check for null");
		System.out.println(login.getEmail() +" " +login.getPassword());
		
		try {
			if (login!=null) {
				
				System.out.println("if not null than takes email and password from the form");
				user = userDao.select(login.getEmail(), login.getPassword());		
			}
		} catch (Exception e) {
			System.out.println("No user found");
		}
		return user ;
	}

	@Override
	public User1 getByEmail(String email) {
		
		return userDao.getByEmail(email);
	}

}