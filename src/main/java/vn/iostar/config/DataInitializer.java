package vn.iostar.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.iostar.entity.User;
import vn.iostar.reponsitory.CategoryRepository;
import vn.iostar.reponsitory.UserRepository;
import vn.iostar.reponsitory.VideoRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepo,
                                   CategoryRepository categoryRepo,
                                   VideoRepository videoRepo) {
        return _ -> {
            
            userRepo.save(new User(null,"admin","123"));
                       
        };
    }
}
