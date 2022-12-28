package com.example.cybersamurai.CyberSamuraiGameStore.service;

import java.util.List;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Platform;



public interface PlatformService {
	public List<Platform> getAll();

	public Platform get(int id);

	public Platform create(Platform platform);

	public Platform update(int id, Platform platform);

	public boolean delete(int id);

}
