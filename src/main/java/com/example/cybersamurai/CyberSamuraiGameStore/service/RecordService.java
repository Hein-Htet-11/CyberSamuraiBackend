package com.example.cybersamurai.CyberSamuraiGameStore.service;

import java.util.List;


import com.example.cybersamurai.CyberSamuraiGameStore.entity.Record;


public interface RecordService {

	public List<Record> getAll();

	public Record get(int id);

	public Record create(Record record);

	public Record update(int id, Record record);

	public boolean delete(int id);

	public List<Record> getAllByUserID(int userID);
	
//	public List<Record> getAllHighestSells(int gameId);
//	
//	public List<Record> getAllHighestWishlists(int gameId);
//	
//	public List<Record> getAllNewGames(int gameId);


}
