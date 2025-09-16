package vn.iostar.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.entity.Category;
import vn.iostar.entity.User;
import vn.iostar.reponsitory.CategoryRepository;
import vn.iostar.service.CategoryService;


@Service
public class CategoryImpl implements CategoryService{
	@Autowired
	CategoryRepository cateRes;

	@Override
	public <S extends Category> S save(S entity) {
		return cateRes.save(entity);
	}

	@Override
	public List<Category> findAll() {
		return cateRes.findAll();
	}

	@Override
	public void deleteById(Long id) {
		cateRes.deleteById(id);
	}

	@Override
	public Category findByName(String name) {
		return cateRes.findByCategoryName(name);
	}

	@Override
	public Category findById(Long id) {
		return cateRes.findById(id).orElse(null);
	}
	
	@Override
	public List<Category> findByCategoryNameContainsCategory(String name,User user){
		return cateRes.findByCategoryNameContainingAndUser(name,user);
	}
	
}
