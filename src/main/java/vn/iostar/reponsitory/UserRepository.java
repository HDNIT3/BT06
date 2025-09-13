package vn.iostar.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
