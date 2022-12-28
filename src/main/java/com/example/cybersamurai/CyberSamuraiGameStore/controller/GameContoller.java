package com.example.cybersamurai.CyberSamuraiGameStore.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Category;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Game;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Platform;
import com.example.cybersamurai.CyberSamuraiGameStore.service.CategoryService;
import com.example.cybersamurai.CyberSamuraiGameStore.service.GameService;
import com.example.cybersamurai.CyberSamuraiGameStore.service.PlatformService;
import com.example.cybersamurai.CyberSamuraiGameStore.service.StorageService;

@RestController
public class GameContoller {

	@Autowired
	GameService gameService;

	@Autowired
	StorageService storageService;

	@Autowired
	CategoryService categoryServie;

	@Autowired
	PlatformService platformService;

	@GetMapping("/game")
	public List<Game> getGames() {
		return gameService.getAll();
	}

	@GetMapping("/game/category/{category_id}")
	public ResponseEntity<?> getGamesByCategory(@PathVariable("category_id") int categoryID) {
		Category category = categoryServie.get(categoryID);
		if (category == null) {
			return ResponseEntity.badRequest().body("Category ID is invalid");
		}
		List<Game> gameList = gameService.getAllByCategory(category);
		return ResponseEntity.ok().body(gameList);
	}

	@GetMapping("/game/platform/{platform_id}")
	public ResponseEntity<?> getGamesByPlatform(@PathVariable("platform_id") int platformID) {
		Platform platform = platformService.get(platformID);
		if (platform == null) {
			return ResponseEntity.badRequest().body("Platform ID is invalid");
		}
		List<Game> gameList = gameService.getAllByPlatform(platform);
		return ResponseEntity.ok().body(gameList);
	}

	@GetMapping("/game/{game_id}")
	public ResponseEntity<Game> getGame(@PathVariable("game_id") int gameID) {
		Game game = gameService.get(gameID);
		if (game == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(game);
	}

	@GetMapping("/media/{fileType}/{fileName}")
	public ResponseEntity<?> getPoster(@PathVariable("fileType") String fileType,
			@PathVariable("fileName") String fileName) throws IOException {
		MediaType contentType = MediaType.IMAGE_PNG;
		switch (fileType) {
		case "mp4":
			contentType = MediaType.APPLICATION_OCTET_STREAM;
			break;
		case "jpg":
			contentType = MediaType.IMAGE_JPEG;
			break;
		case "png":
			contentType = MediaType.IMAGE_PNG;
			break;
		default:
			return ResponseEntity.badRequest().body("Unsupported File Type");
		}
		byte[] fileBytes = storageService.load(fileName);
		if (fileBytes == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().contentType(contentType).body(fileBytes);
	}

	@GetMapping(value = "/game/high_sells")
	public ResponseEntity<List<Game>> getAllHighestSells(int high_sells) {
		List<Game> games = gameService.getAllHighestSells(high_sells);
		return ResponseEntity.ok().body(games);
	}
	
	@GetMapping(value = "/game/wishList")
	public ResponseEntity<List<Game>> getGameWithHighestWishLists(int wishList) {
		List<Game> games = gameService.getAllHighestWishlists(wishList);
		return ResponseEntity.ok().body(games);
	}
	
	@GetMapping(value = "/game/new_games")
	public ResponseEntity<List<Game>> getNewGame(int new_games) {
		List<Game> games = gameService.getAllNewGames(new_games);
		return ResponseEntity.ok().body(games);
	}

}
