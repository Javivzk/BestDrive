package com.svalero.bestread.domain;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;
}
