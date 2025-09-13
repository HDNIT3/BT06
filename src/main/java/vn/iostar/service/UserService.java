package vn.iostar.service;

import vn.iostar.entity.User;

public interface UserService {
	
	User findByUsername(String name);
	
	Boolean checklogin(String name,String pass);
}
