package vn.iostar.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.entity.User;
import vn.iostar.reponsitory.UserRepository;
import vn.iostar.service.UserService;

@Service
public class UserImpl implements UserService{
	@Autowired
	UserRepository userRes;
	
	@Override
	public User findByUsername(String username) {
	    List<User> users = userRes.findByUsername(username);

	    if (users.isEmpty()) {
	        return null;
	    }

	    return users.get(0);
	}

	@Override
	public Boolean checklogin(String username, String password) {
		User a = findByUsername(username);
		if (a==null) {
			return false;
		}		
		if (a.getPassword().contains(password)) {
			return true;
		}
		return false;
	}
	
}
