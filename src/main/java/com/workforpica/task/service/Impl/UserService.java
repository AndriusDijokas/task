package com.workforpica.task.service.Impl;

import com.workforpica.task.controller.payload.auth.SignUpRequest;
import com.workforpica.task.entity.AuthProvider;
import com.workforpica.task.entity.User;
import com.workforpica.task.exception.BadRequestException;
import com.workforpica.task.model.UserDto;
import com.workforpica.task.repository.UserRepository;
import com.workforpica.task.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService implements IUserService {

    final
    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*For Rest response usage*/
    public UserDto getUserById(Long id){
        User user = getUserEntityById(id);
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    /* For internal process usage*/
    public User getUserEntityById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found"));
    }

    public Long registerUser(SignUpRequest signUpRequest){
        if (userRepository.existsByEmail(signUpRequest.getEmail())) new BadRequestException("User email already exists");
        User user =  new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setProvider(AuthProvider.local);
        return userRepository.save(user).getId();
    }
}
