package vn.iostar.reponsitory;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByCategoryName(String categoryName);
}
