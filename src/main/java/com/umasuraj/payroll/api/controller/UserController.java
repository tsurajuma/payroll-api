package com.umasuraj.payroll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umasuraj.payroll.api.payload.UserDto;
import com.umasuraj.payroll.api.response.ApiResponse;
import com.umasuraj.payroll.api.service.UserService;

/**
 *
 * @author umasuraj
 */


@RestController
@RequestMapping("/payroll/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	// POST save user
	@PostMapping("/user/add")
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {

		UserDto savedUserDto = this.userService.saveUser(userDto);

		return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
	}

	// PUT update user
	@PutMapping("/user/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Long userId) {

		UserDto updatedUserDto = this.userService.updateUser(userDto, userId);
		
		return new ResponseEntity(updatedUserDto, HttpStatus.ACCEPTED);
	}

	// DELETE delete user
	@DeleteMapping("/user/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Long userId) {

		this.userService.deleteUserById(userId);

		ApiResponse apiResponse = new ApiResponse("SUCCESS", "USER_DELETED");
		
		return new ResponseEntity(apiResponse, HttpStatus.OK);

	}

	// GET  user
	@GetMapping("/user/get/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Long userId) {

		UserDto foundUserDto = this.userService.getUserById(userId);

		return new ResponseEntity<>(foundUserDto, HttpStatus.FOUND);
	}

	// GET find user list
	@GetMapping("/user/get/all")
	public ResponseEntity<List<UserDto>> getAllUser() {

		List<UserDto> foundUserDtoList = this.userService.getAllUser();

		return new ResponseEntity<>(foundUserDtoList, HttpStatus.FOUND);
	}

}
// end class UserController{}
