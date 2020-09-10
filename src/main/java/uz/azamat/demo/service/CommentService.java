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

import static uz.azamat.demo.filter.LogAndPassFilter.getCurrentUser;

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

    public void saveComment(Comment comment, int id) {
        int currentUserId = getCurrentUser().getId();
        Application app = applicationRepository.findById(id);
        User user = userRepository.findById(currentUserId);
        comment.setApplication(app);
        comment.setUser(user);

        commentRepository.save(comment);
    }

    public CommentDto updateComment(Comment comment, int id) {
        Comment updatedComment = commentRepository.findById(id);
        CommentDto commentDto = new CommentDto();
        updatedComment.setId(id);
        updatedComment.setText(comment.getText());


        updatedComment = commentRepository.save(updatedComment);

        commentDto.setId(updatedComment.getId());
        commentDto.setText(updatedComment.getText());
        commentDto.setUserId(updatedComment.getUser().getId());
        commentDto.setApplicationId(updatedComment.getApplication().getId());

        return commentDto;
    }

    public List<CommentDto> getAllComments() {
        List<CommentDto> list = new ArrayList<>();
        for (Comment c : commentRepository.findAll()) {
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
