package com.unla.grupo13.TrabajoPractico.services;

import com.unla.grupo13.TrabajoPractico.entities.User;
import com.unla.grupo13.TrabajoPractico.models.UserModel;

public interface IUserService {

	
	
	public UserModel save(User user);
	public UserModel findByUserName(String userName);
	public UserModel editar(User user);
	public User findById(int id);
}
