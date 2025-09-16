package vn.iostar.reponsitory;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.entity.Category;
import vn.iostar.entity.User;



public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByCategoryName(String categoryName);
	List<Category> findByCategoryNameContainingAndUser(String categoryName, User user);
}
