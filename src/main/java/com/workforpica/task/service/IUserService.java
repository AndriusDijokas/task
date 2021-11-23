package com.workforpica.task.service;


import com.workforpica.task.controller.payload.auth.SignUpRequest;
import com.workforpica.task.entity.User;
import com.workforpica.task.model.UserDto;

public interface IUserService {
     UserDto getUserById(Long id);
     User getUserEntityById(Long id);
     Long registerUser(SignUpRequest signUpRequest);
}
