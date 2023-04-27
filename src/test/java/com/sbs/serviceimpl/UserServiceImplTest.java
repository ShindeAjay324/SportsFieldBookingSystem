package com.sbs.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.sbs.dto.UserDto;
import com.sbs.repository.UserRepository;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	@Mock
	private UserRepository userRepository;
	
	private UserServiceImpl serviceImpl;
	
	AutoCloseable autoCloseable;
	
	@BeforeEach
	void setUp() {
		AutoCloseable autoCloseable= MockitoAnnotations.openMocks(this);
		this.serviceImpl=new UserServiceImpl(this.userRepository);
	}
	
	void tearDown() {
		try {
			this.autoCloseable.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void registerTest() {
		UserDto dto=new UserDto();
		dto.setEmail("a@gmail.com");
		dto.setName("jay");
		dto.setPassword("234");
		
		UserDto userDto= serviceImpl.register(dto);
		assertEquals(dto.getEmail(), userDto.getEmail());
		assertEquals(dto.getName(), userDto.getName());
		assertEquals(dto.getPassword(), userDto.getPassword());
		
		
	}
	
	@Test
	void getUserTest() {
		serviceImpl.getUsers();
		verify(userRepository).findAll();
	}
	
	

}
