package vn.iostar.service;

import java.util.List;

import vn.iostar.entity.Category;
import vn.iostar.entity.User;

public interface CategoryService {

	void deleteById(Long id);

	List<Category> findAll();

	<S extends Category> S save(S entity);
	
	Category findByName(String name);

	Category findById(Long id);

	List<Category> findByCategoryNameContainsCategory(String name,User user);
}
