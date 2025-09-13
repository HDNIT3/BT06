package vn.iostar.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.entity.Category;
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
	public List<Category> findByUserid(Long userid) {
		return cateRes.findByUserid(userid);
	}
	
}
