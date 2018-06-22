package com.bridgeit.service;

import com.bridgeit.model.Login;
import com.bridgeit.model.User1;

public interface IUserService {
	int register(User1 user);
	User1 login(Login login);
	User1 getByEmail(String name);
}