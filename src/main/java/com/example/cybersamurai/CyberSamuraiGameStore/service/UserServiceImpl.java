package com.example.cybersamurai.CyberSamuraiGameStore.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cybersamurai.CyberSamuraiGameStore.entity.User;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.UserRole;
import com.example.cybersamurai.CyberSamuraiGameStore.entity.UserStatus;
import com.example.cybersamurai.CyberSamuraiGameStore.repository.UserRepo;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	PasswordEncoder pwEncoder;

	@Override
	public User create(User user) {
		// Check IF user with same gmail exists
		User tempGmailCheckUser = userRepo.findByGmail(user.getGmail());
		if (tempGmailCheckUser != null) {
			return null;
		}
		// Create User
		user.setRole(UserRole.user);
		user.setStartJoinDate(LocalDate.now());
		user.setStatus(UserStatus.active);
		user.setPassword(pwEncoder.encode(user.getPassword()));
		user.setCreatedAt(LocalDateTime.now());
		return userRepo.save(user);
	}

	@Override
	public User get(int id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public List<User> getAll() {
		return userRepo.findAll();
	}

	@Override
	public User update(int id, User user) {
		User toUpdateUser = this.get(id);
		if (toUpdateUser == null) {
			return null;
		}
		toUpdateUser.setName(user.getName());
		toUpdateUser.setUpdatedAt(LocalDateTime.now());
		return userRepo.save(toUpdateUser);
	}

	@Override
	public boolean delete(int id) {
		User user = this.get(id);
		if (user == null) {
			return false;
		}
		userRepo.deleteById(id);
		return true;
	}

	@Override
	public User checkLoginUser(String gmail, String password) {
		User user = userRepo.findByGmail(gmail);
		if (user == null) {
			return null;
		}
		if (!pwEncoder.matches(password, user.getPassword())) {
			return null;
		}
		user.setAccessCount(user.getAccessCount() + 1);
		return userRepo.save(user);
	}

	@Override
	public User updateStatus(int id, String status) {
		User toUpdateUser = this.get(id);
		if (toUpdateUser == null) {
			return null;
		}
		try {
			toUpdateUser.setStatus(UserStatus.valueOf(status));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
		toUpdateUser.setUpdatedAt(LocalDateTime.now());
		userRepo.save(toUpdateUser);
		return toUpdateUser;
	}

	@Override
	public void updatePwd(int id, String newPwd) {

		User toUpdateUserPwd = get(id);
		if (toUpdateUserPwd != null) {
			toUpdateUserPwd.setPassword(pwEncoder.encode(newPwd));
			userRepo.save(toUpdateUserPwd);
			System.out.println("pwd updated");
		}

	}

	@Override
	public List<String> getAllStatus() {
		List<String> userStatusList = new ArrayList<>();
		for (UserStatus status : Arrays.asList(UserStatus.values())) {
			userStatusList.add(status.toString());
		}
		return userStatusList;
	}

}
