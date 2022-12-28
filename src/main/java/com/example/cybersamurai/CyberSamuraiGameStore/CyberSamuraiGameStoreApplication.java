package com.example.cybersamurai.CyberSamuraiGameStore;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Category;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Platform;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.User;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.UserRole;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.UserStatus;
import com.example.cybersamurai.CyberSamuraiGameStore.service.CategoryService;
import com.example.cybersamurai.CyberSamuraiGameStore.service.PlatformService;
import com.example.cybersamurai.CyberSamuraiGameStore.service.StorageService;
import com.example.cybersamurai.CyberSamuraiGameStore.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class CyberSamuraiGameStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CyberSamuraiGameStoreApplication.class, args);
	}

	@Autowired
	UserService userService;

	@Autowired
	StorageService storageService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	PlatformService platformService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlMode;

	@Value("${custom.delete.files}")
	private String deleteFiles;

	@Override
	public void run(String... args) throws Exception {

		if (ddlMode.equals("create")) {

			userService.create(new User(1, "Admin", "1111", "admin@gmail.com", UserStatus.active, UserRole.admin,
					LocalDate.now(), null, 0, LocalDateTime.now(), null));
			categoryService.create(new Category(1, "Action"));
			categoryService.create(new Category(2, "Action-Adventure"));
			categoryService.create(new Category(3, "Action RPG"));
			categoryService.create(new Category(4, "Sports"));
			categoryService.create(new Category(5, "Simulation"));
			categoryService.create(new Category(6, "Multiplayer"));
			categoryService.create(new Category(7, "Strategy"));
			categoryService.create(new Category(8, "Horror"));
			categoryService.create(new Category(9, "Fighting"));
			categoryService.create(new Category(10, "Racing"));
			categoryService.create(new Category(11, "Platformer"));

			platformService.create(new Platform(1, "PS5"));
			platformService.create(new Platform(2, "PS4"));
			platformService.create(new Platform(3, "XBOX"));
			platformService.create(new Platform(4, "NINTENDO SWIITCH"));

		}

		if (deleteFiles.equals("true")) {
			storageService.clearAll();
		}

	}

}
