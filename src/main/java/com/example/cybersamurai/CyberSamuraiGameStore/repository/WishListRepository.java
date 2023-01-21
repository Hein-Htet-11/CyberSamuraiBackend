package com.example.cybersamurai.CyberSamuraiGameStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.User;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.WishList;

@EnableJpaRepositories
public interface WishListRepository extends JpaRepository<WishList, Integer>{

	public List<WishList> findAllByUser(User user);

}
