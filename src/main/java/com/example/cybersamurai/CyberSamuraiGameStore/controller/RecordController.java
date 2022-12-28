package com.example.cybersamurai.CyberSamuraiGameStore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.Record;
import com.example.cybersamurai.CyberSamuraiGameStore.service.RecordService;



@RestController
public class RecordController {

	@Autowired
	RecordService recordService;

	@GetMapping("/record")
	public List<Record> getRecords(@RequestParam("userID") int userID) {
		return recordService.getAllByUserID(userID);
	}
	
//	@GetMapping("/record/highest_sells")
//	public List<Record> getHighestSellRecords(@RequestParam("gameID") int gameID) {
//		return recordService.getAllHighestSells(gameID);
//	}

	@PostMapping("/record/add")
	public ResponseEntity<?> addRecord(@Valid @RequestBody Record record) {
		if (record.getUser().getId() <= 0) {
			return ResponseEntity.badRequest().body("User is invalid");
		}
		if (record.getGame().getId() <= 0) {
			return ResponseEntity.badRequest().body("Game is invalid");
		}
		Record createdRecord = recordService.create(record);
		if (createdRecord == null) {
			return ResponseEntity.badRequest().body(
					"User not found, Game not found. User role not user"
			);
		}
		return ResponseEntity.ok().body(createdRecord);
	}

}
