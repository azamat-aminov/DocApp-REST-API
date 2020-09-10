package uz.azamat.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.azamat.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByLogin(String login);
}
