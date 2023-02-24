package com.umasuraj.payroll.api.service.implementation;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umasuraj.payroll.api.entity.User;
import com.umasuraj.payroll.api.payload.UserDto;
import com.umasuraj.payroll.api.repository.UserRepository;
import com.umasuraj.payroll.api.service.UserService;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 *
 * @author umasuraj
 */

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {

        User user = this.modelMapper
                .map(userDto, User.class);
        LocalDateTime userAddTimestamp = LocalDateTime.now();
        user.setAddTimestamp(userAddTimestamp);

        User savedUser = this.userRepository.save(user);

        UserDto savedUserDTO = this.modelMapper
                .map(savedUser, UserDto.class);

        return savedUserDTO;
    }
    // end saveUser() method

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {

        User user = this.userRepository.findById(userId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setDesignation(userDto.getDesignation());

        LocalDateTime userUpdateTimestamp = LocalDateTime.now();
        user.setUpdateTimestamp(userUpdateTimestamp);

        User updatedUser = this.userRepository.save(user);

        UserDto updatedUserDTO = this.modelMapper
                .map(updatedUser, UserDto.class);

        return updatedUserDTO;
    }
    // end updateUser() method

    @Override
    public UserDto getUserById(Long userId) {

        User foundUser = this.userRepository.findById(userId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));

        UserDto foundUserDto = this.modelMapper
                .map(foundUser, UserDto.class);

        return foundUserDto;
    }
    // end findUserById() method

    @Override
    public List<UserDto> getAllUser() {

        List<User> userList = new ArrayList<>();

        userList = this.userRepository.findAll();

        List<UserDto> userDtoList = userList.stream()
                .map(user -> this.modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        return userDtoList;
    }
    // end getAllUser() method

    @Override
    public void deleteUserById(Long userId) {

        User user = this.userRepository.findById(userId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));
        this.userRepository.delete(user);
    }
    // end deleteUserById() method

}
//end class UserServiceImplementation{}
