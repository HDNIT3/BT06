package vn.iostar.service.Impl;

import java.util.List;
import java.util.Optional;

import vn.iostar.entity.User;
import vn.iostar.reponsitory.UserRepository;
import vn.iostar.service.UserService;

public class UserImpl implements UserService{
	UserRepository userRes;

	@Override
	public List<User> findAll() {
		return userRes.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRes.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		userRes.deleteById(id);
	}
}
