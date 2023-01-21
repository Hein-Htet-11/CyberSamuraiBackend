package com.example.cybersamurai.CyberSamuraiGameStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.User;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Game;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Record;

@EnableJpaRepositories
public interface RecordRepo extends JpaRepository<Record, Integer> {
	
	public List<Record> findAllByUser(User user);
	
//	@Query(value="select * from game where soldQty desc limit 3", nativeQuery=true)
//	public List<Record> findByHighestSells(Game game);
//
//	@Query(value="select * from game where wishList desc limit 3", nativeQuery=true)
//	public List<Record> findByHighestWishList(Game game);
//	
//	@Query(value="select * from game where id desc limit 3", nativeQuery=true)
//	public List<Record> findByNewlyAdded(Game game);

}
