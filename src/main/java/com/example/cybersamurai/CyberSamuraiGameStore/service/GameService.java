package com.example.cybersamurai.CyberSamuraiGameStore.service;

import java.util.List;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Category;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Game;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Platform;

public interface GameService {

	public List<Game> getAll();

	public Game get(int id);

	public Game create(Game game);

	public Game update(int id, Game game);
	
	public Game wish_list(int id, int wishList);

	public boolean delete(int id);

	public List<Game> getAllByCategory(Category category);

	public List<Game> getAllByPlatform(Platform platform);

	public Game getByTitle(String title);

	public List<Game> getAllHighestSells(int soldQty);

	public List<Game> getAllHighestWishlists(int wishList);

	public List<Game> getAllNewGames(int game_id);

}
