package com.sbs.service;

import java.util.List;

import com.sbs.dto.UserDto;
import com.sbs.dto.UserDtowithOutPass;
import com.sbs.entity.User;

public interface UserService {

	UserDto register(UserDto userDto);

	User login(UserDto userDto);

	List<UserDtowithOutPass> getUsers();

	UserDtowithOutPass getUser(Integer id);

	Integer deleteUser(Integer id);

	UserDto changeUserInfo(UserDto userDto, Integer id);

}
