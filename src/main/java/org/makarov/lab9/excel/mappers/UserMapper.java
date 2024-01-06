package org.makarov.lab9.excel.mappers;

import org.makarov.lab9.excel.dto.UserDTO;
import org.makarov.lab9.models.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
