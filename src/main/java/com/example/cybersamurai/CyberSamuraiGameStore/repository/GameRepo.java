package com.example.cybersamurai.CyberSamuraiGameStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Category;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Game;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Platform;

@EnableJpaRepositories
public interface GameRepo extends JpaRepository<Game, Integer> {

	public List<Game> findByCategory(Category category);
	
	public List<Game> findByPlatform(Platform platform);

	public Game findByTitle(String title);

	@Query(value="select * from game where soldQty desc limit 3", nativeQuery=true)
	public List<Game> findByHighestSells(int soldQty);

	@Query(value="select * from game where wishList desc limit 3", nativeQuery=true)
	public List<Game> findByHighestWishList(int wishList);
	
	@Query(value="select * from game where id desc limit 3", nativeQuery=true)
	public List<Game> findByNewlyAdded(int game_id);

}
