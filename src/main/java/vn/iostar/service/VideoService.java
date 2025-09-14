package vn.iostar.service;

import java.util.List;

import vn.iostar.entity.Video;

public interface VideoService {


	void deleteById(Long id);

	List<Video> findAll();

	<S extends Video> S save(S entity);
	
}
