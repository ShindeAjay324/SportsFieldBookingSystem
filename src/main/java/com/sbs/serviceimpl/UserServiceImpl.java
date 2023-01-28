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
import com.sbs.exception.NameNotFoundException;
import com.sbs.exception.UserNotFoundException;
import com.sbs.repository.UserRepository;
import com.sbs.service.UserService;

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
		return userDto;
	}

	@Override
	public User login(UserDto userDto) {
		User user = userRepository.findByEmail(userDto.getEmail())
				.orElseThrow(()-> new EmailNotFoundException("Email address not found"+ userDto.getEmail()));
		if (user != null && userDto.getEmail().equals(user.getEmail())
				&& userDto.getPassword().equals(user.getPassword())) {
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
		return dtos;
	}

	@Override
	public UserDtowithOutPass getUser(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found" + id));
		UserDtowithOutPass dtowithOutPass = new UserDtowithOutPass();
		BeanUtils.copyProperties(user, dtowithOutPass);
		return dtowithOutPass;
	}

	@Override
	public Integer deleteUser(Integer id) {
		User user = userRepository.findById(id).
				orElseThrow(() -> new UserNotFoundException("User not found" + id));
		userRepository.deleteById(id);
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

		return newUserDto;
	}

	protected void deleteAll() {
		userRepository.deleteAll();
	}

}
