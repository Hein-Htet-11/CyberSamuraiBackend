package com.example.cybersamurai.CyberSamuraiGameStore.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Game;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Record;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.User;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.UserRole;
import com.example.cybersamurai.CyberSamuraiGameStore.repository.GameRepo;
import com.example.cybersamurai.CyberSamuraiGameStore.repository.RecordRepo;
import com.example.cybersamurai.CyberSamuraiGameStore.repository.UserRepo;


@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	RecordRepo recordRepo;

	@Autowired
	UserRepo userRepo;
	@Autowired
	GameRepo gameRepo;

	@Override
	public Record create(Record record) {
		// Check User
		User user = userRepo.findById(record.getUser().getId()).orElse(null);
		if (user == null) {
			return null;
		}
		// Check Game
		Game game = gameRepo.findById(record.getGame().getId())
				.orElse(null);
		if (game == null) {
			return null;
		}
		// Create Record Only If user role is user
		if (user.getRole() == UserRole.user) {
			record.setCreatedAt(LocalDateTime.now());
			return recordRepo.save(record);
		}
		return null;
	}

	@Override
	public Record get(int id) {
		return recordRepo.findById(id).orElse(null);
	}

	@Override
	public List<Record> getAll() {
		return recordRepo.findAll();
	}

	@Override
	public Record update(int id, Record rec) {
		Record record = this.get(id);
		if (record == null) {
			return null;
		}
		record.setUser(rec.getUser());
		record.setGame(rec.getGame());
		record.setUpdatedAt(LocalDateTime.now());
		recordRepo.save(record);
		return record;
	}

	@Override
	public boolean delete(int id) {
		Record record = this.get(id);
		if (record == null) {
			return false;
		}
		recordRepo.deleteById(id);
		return true;
	}

	@Override
	public List<Record> getAllByUserID(int userID) {
		User user = new User();
		user.setId(userID);
		return recordRepo.findAllByUser(user);
	}

//	@Override
//	public List<Record> getAllHighestSells(int gameId) {
//		Game game = new Game();
//		game.setId(gameId);
//		return recordRepo.findByHighestSells(game);
//	}
//
//	@Override
//	public List<Record> getAllHighestWishlists(int gameId) {
//		Game game = new Game();
//		game.setId(gameId);
//		return recordRepo.findByHighestWishList(game);
//	}
//
//	@Override
//	public List<Record> getAllNewGames(int gameId) {
//		Game game = new Game();
//		game.setId(gameId);
//		return recordRepo.findByNewlyAdded(game);
//	}

}
