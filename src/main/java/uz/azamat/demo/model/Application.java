package uz.azamat.demo.model;

import lombok.Data;

import java.sql.Date;
@Data
public class Application {
    private int id;
    private String heading;
    private String text;
    private Date date;
}
