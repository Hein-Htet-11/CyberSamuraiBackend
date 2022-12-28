package com.example.cybersamurai.CyberSamuraiGameStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.User;



public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByGmail(String gmail);

	public User findByName(String name);

}
