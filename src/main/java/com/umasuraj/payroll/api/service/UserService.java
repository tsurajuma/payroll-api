package com.umasuraj.payroll.api.service;

import java.util.List;

import com.umasuraj.payroll.api.payload.UserDto;

/**
 *
 * @author umasuraj
 */

public interface UserService {

    public UserDto saveUser(UserDto userDto);

    public UserDto updateUser(UserDto userDto, Long userId);

    public UserDto getUserById(Long userId);

    public List<UserDto> getAllUser();

    public void deleteUserById(Long userId);

}
// end interface UserService{}
