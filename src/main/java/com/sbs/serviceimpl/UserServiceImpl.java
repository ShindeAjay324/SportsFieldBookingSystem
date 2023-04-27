package com.sbs.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.dto.UserDto;
import com.sbs.dto.UserDtowithOutPass;
import com.sbs.entity.User;
import com.sbs.exception.EmailNotFoundException;
import com.sbs.exception.UserNotFoundException;
import com.sbs.repository.UserRepository;
import com.sbs.service.UserService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDto register(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		userRepository.save(user);
		log.info("User register Successfully, Id of user is "+user.getUserId() );
		return userDto;
	}

	@Override
	public User login(UserDto userDto) {
		User user = userRepository.findByEmail(userDto.getEmail())
				.orElseThrow(()-> new EmailNotFoundException("Email address not found"+ userDto.getEmail()));
		if (user != null && userDto.getEmail().equals(user.getEmail())
				&& userDto.getPassword().equals(user.getPassword())) {
			log.info("User login Successfully, Id of user is "+user.getUserId() );
			return user;
		}
		return null;
	}

	@Override
	public List<UserDtowithOutPass> getUsers() {
		List<User> users = userRepository.findAll();
		List<UserDtowithOutPass> dtos = new ArrayList<>();
		users.forEach(e -> {
			UserDtowithOutPass dto = new UserDtowithOutPass();
			BeanUtils.copyProperties(e, dto);
			dtos.add(dto);
		});
		log.info("All users fetch successfully");
		return dtos;
	}

	@Override
	public UserDtowithOutPass getUser(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found" + id));
		UserDtowithOutPass dtowithOutPass = new UserDtowithOutPass();
		BeanUtils.copyProperties(user, dtowithOutPass);
		log.info("User fetch successfully, userId is "+user.getUserId());
		return dtowithOutPass;
	}

	@Override
	public Integer deleteUser(Integer id) {
		User user = userRepository.findById(id).
				orElseThrow(() -> new UserNotFoundException("User not found" + id));
		userRepository.deleteById(id);
		log.info("User remove from DB successfully, id of user is "+id);
		return user.getUserId();
	}

	@Override
	public UserDto changeUserInfo(UserDto userDto, Integer id)  {
		User user = userRepository.findById(id).
				orElseThrow(() -> new UserNotFoundException("User not found" + id));
		if (userDto.getEmail() != null) {
			user.setEmail(userDto.getEmail());
		}else {
			throw new EmailNotFoundException("Email address not found"+ userDto.getEmail());
		}
		if (userDto.getName() != null) {
			user.setName(userDto.getName());
		}
		userRepository.save(user);
		UserDto newUserDto = new UserDto();
		BeanUtils.copyProperties(user, newUserDto);
		log.info("User info updated successfully, userId is "+user.getUserId());
		return newUserDto;
	}

	protected void deleteAll() {
		userRepository.deleteAll();
		log.info("All user data deleted");
	}

}
