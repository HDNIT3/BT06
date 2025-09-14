package vn.iostar.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.entity.Video;
import vn.iostar.reponsitory.VideoRepository;
import vn.iostar.service.VideoService;

@Service
public class VideoImpl implements VideoService{
	@Autowired
	VideoRepository videoRes;

	@Override
	public <S extends Video> S save(S entity) {
		return videoRes.save(entity);
	}

	@Override
	public List<Video> findAll() {
		return videoRes.findAll();
	}

	@Override
	public void deleteById(Long id) {
		videoRes.deleteById(id);
	}
	
	@Override
	public List<Video> findByCategoryid(Long id) {
		return videoRes.findByCategoryid(id);
	}
}
