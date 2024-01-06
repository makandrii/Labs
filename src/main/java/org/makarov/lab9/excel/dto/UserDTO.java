package org.makarov.lab9.excel.dto;

import lombok.Builder;

@Builder
public class UserDTO {
    private int id;
    private String email;
    private String name;
    private String role;
}
