package com.bridgeit.dao;

import com.bridgeit.model.User1;

public interface IUserDao {

	User1 select(String email, String password);

	User1 getByEmail(String email);

	int insert(User1 user);


}