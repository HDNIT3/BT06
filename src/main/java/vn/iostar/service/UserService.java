package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import vn.iostar.entity.User;

public interface UserService {

	void deleteById(Long id);

	Optional<User> findById(Long id);

	List<User> findAll();

}
