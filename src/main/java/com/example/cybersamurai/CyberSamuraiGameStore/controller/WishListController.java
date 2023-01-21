package com.example.cybersamurai.CyberSamuraiGameStore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Category;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.Game;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.WishList;
import com.example.cybersamurai.CyberSamuraiGameStore.service.GameService;
import com.example.cybersamurai.CyberSamuraiGameStore.service.WishListService;

@RestController
public class WishListController {

	@Autowired
	WishListService wishListService;
	
	@Autowired
	GameService gameService;
	
	@GetMapping("/wishlist")
	public List<WishList> getWishLists( @RequestParam("userID") int userID) {
		return wishListService.getAllByUserID(userID);
	}
     
     @PostMapping("/wishlist/add")
 	public ResponseEntity<?> addRecord(@Valid @RequestBody WishList wishList) {
 		if (wishList.getUser().getId() <= 0) {
 			return ResponseEntity.badRequest().body("User is invalid");
 		}
 		if (wishList.getGame().getId() <= 0) {
 			return ResponseEntity.badRequest().body("Game is invalid");
 		}
 		WishList createdWishList = wishListService.create(wishList);
 		if (createdWishList == null) {
 			return ResponseEntity.badRequest().body(
 					"User not found, Game not found. User role not user"
 			);
 		}
 		return ResponseEntity.ok().body(createdWishList);
 	}

}
