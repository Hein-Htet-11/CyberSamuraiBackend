package com.example.cybersamurai.CyberSamuraiGameStore.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Category;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Game;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Platform;
import com.example.cybersamurai.CyberSamuraiGameStore.repository.GameRepo;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameRepo gameRepo;

	@Override
	public Game create(Game game) {
		game.setCreatedAt(LocalDateTime.now());
		return gameRepo.save(game);
	}

	@Override
	public Game get(int id) {
		return gameRepo.findById(id).orElse(null);
	}

	@Override
	public List<Game> getAll() {
		return gameRepo.findAll();
	}

	@Override
	public Game update(int id, Game game) {
		Game toUpdateGame = this.get(id);
		if (toUpdateGame == null) {
			return null;
		}
		toUpdateGame.setTitle(game.getTitle());
		toUpdateGame.setBudget(game.getBudget());
		toUpdateGame.setOverview(game.getOverview());
		toUpdateGame.setCategory(game.getCategory());
		toUpdateGame.setPlatform(game.getPlatform());
		toUpdateGame.setOut_of_stock(game.getOut_of_stock());
		toUpdateGame.setPosterPath(game.getPosterPath());
		toUpdateGame.setTrailerPath(game.getTrailerPath());
		toUpdateGame.setUpdatedAt(LocalDateTime.now());
		gameRepo.save(toUpdateGame);
		return toUpdateGame;
	}

	@Override
	public boolean delete(int id) {
		Game game = this.get(id);
		if (game == null) {
			return false;
		}
		gameRepo.deleteById(id);
		return true;
	}

	@Override
	public List<Game> getAllByCategory(Category category) {
		return gameRepo.findByCategory(category);
	}

	@Override
	public Game getByTitle(String title) {
		return gameRepo.findByTitle(title);
	}

	@Override
	public List<Game> getAllByPlatform(Platform platform) {
		return gameRepo.findByPlatform(platform);
	}

	@Override
	public List<Game> getAllHighestSells(int soldQty) {
		return gameRepo.findByHighestSells(soldQty);
	}

	@Override
	public List<Game> getAllHighestWishlists(int wishList) {
		return gameRepo.findByHighestWishList(wishList);
	}

	@Override
	public List<Game> getAllNewGames(int game_id) {
		return gameRepo.findByNewlyAdded(game_id);
	}

	@Override
	public Game wish_list(int id, int wishList) {
		Game wishListedGame = this.get(id);
		if (wishListedGame == null) {
			return null;
		}
		wishListedGame.setWishList(wishList);
		gameRepo.save(wishListedGame);
		return wishListedGame;
	}

}
