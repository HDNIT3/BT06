package vn.iostar.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.entity.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByUsername(String username);
}
