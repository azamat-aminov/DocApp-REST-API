package uz.azamat.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.azamat.demo.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findById(int id);
}
