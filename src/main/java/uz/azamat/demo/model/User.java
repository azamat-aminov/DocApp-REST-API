package uz.azamat.demo.model;

import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private Date date;
    private String address;
    private String login;
    private String password;
}
