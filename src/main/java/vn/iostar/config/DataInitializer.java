package vn.iostar.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.iostar.entity.Category;
import vn.iostar.entity.User;
import vn.iostar.entity.Video;
import vn.iostar.reponsitory.CategoryRepository;
import vn.iostar.reponsitory.UserRepository;
import vn.iostar.reponsitory.VideoRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepo,
                                   CategoryRepository categoryRepo,
                                   VideoRepository videoRepo) {
        return args -> {
            
            userRepo.save(new User(1L,"admin","admin123"));
            
            categoryRepo.save(new)
            
            
            
            
        };
    }
}
