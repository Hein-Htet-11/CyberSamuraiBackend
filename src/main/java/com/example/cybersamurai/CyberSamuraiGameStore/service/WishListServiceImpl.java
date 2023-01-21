package com.example.cybersamurai.CyberSamuraiGameStore.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Game;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.User;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.UserRole;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.WishList;
import com.example.cybersamurai.CyberSamuraiGameStore.repository.GameRepo;
import com.example.cybersamurai.CyberSamuraiGameStore.repository.UserRepo;
import com.example.cybersamurai.CyberSamuraiGameStore.repository.WishListRepository;

@Service
public class WishListServiceImpl implements WishListService{

	@Autowired
	WishListRepository wishListRepo;

	@Autowired
	UserRepo userRepo;
	@Autowired
	GameRepo gameRepo;
	
	@Override
	public WishList create(WishList wishList) {
		// Check User
		User user = userRepo.findById(wishList.getUser().getId()).orElse(null);
		if (user == null) {
			return null;
		}
		// Check Game
		Game game = gameRepo.findById(wishList.getGame().getId())
				.orElse(null);
		if (game == null) {
			return null;
		}
		// Create WishList Only If user role is user
		if (user.getRole() == UserRole.user) {
			wishList.setCreatedAt(LocalDateTime.now());
			return wishListRepo.save(wishList);
		}
		return null;
	}

	@Override
	public WishList get(int id) {
		return wishListRepo.findById(id).orElse(null);
	}

	@Override
	public List<WishList> getAll() {
		return wishListRepo.findAll();
	}


	@Override
	public boolean delete(int id) {
		WishList wishList = this.get(id);
		if (wishList == null) {
			return false;
		}
		wishListRepo.deleteById(id);
		return true;
	}

	@Override
	public List<WishList> getAllByUserID(int userID) {
		User user = new User();
		user.setId(userID);
		return wishListRepo.findAllByUser(user);
	}

	@Override
	public WishList update(int id, WishList wl) {
		WishList wishList = this.get(id);
		if (wishList == null) {
			return null;
		}
		wishList.setUser(wl.getUser());
		wishList.setGame(wl.getGame());
		wishList.setUpdatedAt(LocalDateTime.now());
		wishListRepo.save(wishList);
		return wishList;
	}
}
