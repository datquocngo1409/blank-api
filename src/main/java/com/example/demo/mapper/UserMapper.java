package com.example.demo.mapper;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {
    ModelMapper mapper = new ModelMapper();

    public UserDto mapUserToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    public List<UserDto> mapUsersToDtos(List<User> users) {
        List<UserDto> result = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = mapper.map(user, UserDto.class);
            result.add(userDto);
        }
        return result;
    }
}
