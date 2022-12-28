package com.example.cybersamurai.CyberSamuraiGameStore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Category;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Game;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Platform;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.User;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Record;
import com.example.cybersamurai.CyberSamuraiGameStore.service.CategoryService;
import com.example.cybersamurai.CyberSamuraiGameStore.service.GameService;
import com.example.cybersamurai.CyberSamuraiGameStore.service.PlatformService;
import com.example.cybersamurai.CyberSamuraiGameStore.service.RecordService;
import com.example.cybersamurai.CyberSamuraiGameStore.service.StorageService;
import com.example.cybersamurai.CyberSamuraiGameStore.service.UserService;



@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	GameService gameService;

	@Autowired
	StorageService storageService;

	@Autowired
	RecordService recordService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PlatformService platformService;

	// ------------------- Game

	@PostMapping("/game/create")
	public ResponseEntity<?> createGame(@Valid @RequestBody Game game) {
		if (game.getCategory() == null) {
			return ResponseEntity.badRequest().body("Category is empty");
		}
		if (game.getCategory().getId() == 0) {
			return ResponseEntity.badRequest().body("Category ID is invalid");
		}
		if (game.getPlatform() == null) {
			return ResponseEntity.badRequest().body("Platform is empty");
		}
		if (game.getPlatform().getId() == 0) {
			return ResponseEntity.badRequest().body("Platform ID is invalid");
		}
		if (!storageService.check(game.getPosterPath())) {
			return ResponseEntity.badRequest().body("Poster is invalid");
		}
		if (!storageService.check(game.getTrailerPath())) {
			return ResponseEntity.badRequest().body("Trailer is invalid");
		}
		return ResponseEntity.ok(gameService.create(game));
	}

	@PostMapping("/file/create")
	public ResponseEntity<String> createFile(
			@RequestParam("file") MultipartFile file,
			@RequestParam("fileType") String fileType
	) {
		String filePath = storageService.create(file, fileType);
		if (filePath == null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(filePath);
	}

	@PutMapping("/file/update")
	public ResponseEntity<String> updateFile(
			@RequestParam("file") MultipartFile file,
			@RequestParam("fileType") String fileType,
			@RequestParam("filePath") String filePath
	) {
		String newFilePath = storageService.update(file, fileType, filePath);
		if (newFilePath == null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(newFilePath);
	}

	@PutMapping("/game/update/{id}")
	public ResponseEntity<Game> updateGame(
			@PathVariable int id, @Valid @RequestBody Game game
	) {
		Game updatedGame = gameService.update(id, game);
		if (updatedGame == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updatedGame);
	}

	@DeleteMapping(value = "/game/delete/{id}")
	public ResponseEntity<?> deleteGame(@PathVariable int id) {
		Game game = gameService.get(id);
		if (game == null) {
			return ResponseEntity.notFound().build();
		}

		// Delete Game
		boolean isDeleted = gameService.delete(id);
		if (!isDeleted) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}

		// Delete Poster
		storageService.delete(game.getPosterPath());

		// Delete Trailer
		storageService.delete(game.getTrailerPath());

		return ResponseEntity.ok().build();
	}

	@GetMapping("/game/title/{title}")
	public ResponseEntity<Boolean> findGameByTitle(
			@PathVariable("title") String title
	) {
		Game game = gameService.getByTitle(title);
		if (game == null) {
			return ResponseEntity.ok().body(false);
		}
		return ResponseEntity.ok().body(true);
	}

	// ------------------- User

	@GetMapping("/user")
	public List<User> listUser() {
		return userService.getAll();
	}

	@PutMapping("/user/update_status")
	public ResponseEntity<?> updateUserStatus(
			@RequestParam int id, @RequestParam String status
	) {
		User user = userService.updateStatus(id, status);
		if (user == null) {
			return ResponseEntity.badRequest()
					.body("User is invalid, Status is invalid");
		}
		return ResponseEntity.ok(user);
	}

	@GetMapping("/user_status")
	public List<String> listUserStatus() {
		return userService.getAllStatus();
	}

	// ------------------- Record

	@GetMapping("/record")
	public List<Record> listRecord() {
		return recordService.getAll();
	}

	// ------------------- Category

	@GetMapping("/category")
	public List<Category> listCategory() {
		return categoryService.getAll();
	}
	
	@GetMapping("/platform")
	public List<Platform> listPlatform() {
		return platformService.getAll();
	}

}
