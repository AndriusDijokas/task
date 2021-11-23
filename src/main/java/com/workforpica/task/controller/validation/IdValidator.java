package com.workforpica.task.controller.validation;

import com.workforpica.task.controller.validation.annotation.ValidId;
import com.workforpica.task.entity.User;
import com.workforpica.task.repository.CategoryRepository;
import com.workforpica.task.repository.LobbyRepository;
import com.workforpica.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdValidator implements ConstraintValidator<ValidId, Long> {

    private enum IdType {
        UserId,
        CategoryId,
        LobbyId
    }

    private String what;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LobbyRepository lobbyRepository;

    @Override
    public void initialize(ValidId constraintAnnotation) {
        what = constraintAnnotation.what();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean result = true;
        if (IdType.UserId.name().equalsIgnoreCase(getWhat())) {
            result = userRepository.existsById(value);
        }
        if (IdType.LobbyId.name().equalsIgnoreCase(getWhat())) {
            result = lobbyRepository.existsById(value);
        }
        if (IdType.CategoryId.name().equalsIgnoreCase(getWhat())) {
            result = categoryRepository.existsById(value);
        }
        return result;
    }
    private String getWhat() {
        return what;
    }


}
