package com.example.cybersamurai.CyberSamuraiGameStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Platform;

@EnableJpaRepositories
public interface PlatformRepo extends JpaRepository<Platform, Integer>{

}
