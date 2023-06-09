package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {
    private Integer id;
    private String email;
    private String password;
    private Date registrationDate;
}
