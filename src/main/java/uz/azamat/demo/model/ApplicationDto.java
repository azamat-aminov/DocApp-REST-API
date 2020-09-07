package uz.azamat.demo.model;

import lombok.Data;

import java.sql.Date;
@Data
public class ApplicationDto {
    private int id;
    private String heading;
    private String text;
    private Date date;
    private int userId;
}
