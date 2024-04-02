package com.sritech.user.dao;

import java.util.List;

import com.sritech.user.model.User;

public interface UserDAO {
	
	public  abstract List<User> getUsersList();
	
	public abstract  void insertList(User user);

	public abstract User editUser(int userid);

	public abstract boolean updateUser(User user);

	public abstract boolean deleteUser(int id);



	
	

}
