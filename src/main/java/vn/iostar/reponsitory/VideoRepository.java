package vn.iostar.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
	List<Video> findByCategoryid(Long id);
}
