package com.example.cybersamurai.CyberSamuraiGameStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Platform;
import com.example.cybersamurai.CyberSamuraiGameStore.repository.PlatformRepo;

@Service
public class PlatformServiceImpl implements PlatformService {

	@Autowired
	PlatformRepo platformRepo;

	@Override
	public List<Platform> getAll() {

		return platformRepo.findAll();
	}

	@Override
	public Platform get(int id) {

		return platformRepo.findById(id).orElse(null);
	}
	
	@Override
	public Platform create(Platform platform) {

		return platformRepo.save(platform);
	}

	@Override
	public Platform update(int id, Platform platform) {
		Platform toUpdatePlatform = this.get(id);
		if (toUpdatePlatform == null) {
			return null;
		}
		toUpdatePlatform.setId(id);
		toUpdatePlatform.setName(platform.getName());
		return platformRepo.save(platform);
	}

	@Override
	public boolean delete(int id) {
		Platform platform = this.get(id);
		if(platform==null) {
			return false;
		}
		platformRepo.deleteById(id);
		return true;
	}

}
