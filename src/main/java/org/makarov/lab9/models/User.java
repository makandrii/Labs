package org.makarov.lab9.models;

import lombok.Getter;

@Getter
public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String role;
    private String avatar;
}
