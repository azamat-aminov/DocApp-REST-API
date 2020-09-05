package uz.azamat.demo.repository;

import org.springframework.data.repository.CrudRepository;
import uz.azamat.demo.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
