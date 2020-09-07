package uz.azamat.demo.model;

import lombok.Data;

@Data
public class CommentDto {
    private int id;
    private String text;
    private int applicationId;
    private int userId;


}
