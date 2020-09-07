package uz.azamat.demo.service;

import org.springframework.stereotype.Service;
import uz.azamat.demo.model.Application;
import uz.azamat.demo.model.Comment;
import uz.azamat.demo.model.CommentDto;
import uz.azamat.demo.model.User;
import uz.azamat.demo.repository.ApplicationRepository;
import uz.azamat.demo.repository.CommentRepository;
import uz.azamat.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    CommentRepository commentRepository;
    UserRepository userRepository;
    ApplicationRepository applicationRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository,
                          ApplicationRepository applicationRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
    }

    public void saveComment(Comment comment) {
        Application app = applicationRepository.findById(1);
        User user = userRepository.findById(1);
        comment.setApplication(app);
        comment.setUser(user);

        commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment, int id){
        Comment updatedComment = commentRepository.findById(id);
        updatedComment.setId(id);
        updatedComment.setText(comment.getText());

        return commentRepository.save(updatedComment);
    }

    public List<CommentDto> getAllComments() {
        List<CommentDto> list = new ArrayList<>();
        for(Comment c : commentRepository.findAll()) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(c.getId());
            commentDto.setText(c.getText());
            commentDto.setApplicationId(c.getApplication().getId());
            commentDto.setUserId(c.getUser().getId());
            list.add(commentDto);
        }
        return list;
    }
}
