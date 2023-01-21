package com.example.cybersamurai.CyberSamuraiGameStore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.WishList;


@Service
public interface WishListService {



	public List<WishList> getAll();

	public WishList get(int id);

	public WishList create(WishList wishList);

	public WishList update(int id, WishList wishList);

	public boolean delete(int id);

	public List<WishList> getAllByUserID(int userID);

}
