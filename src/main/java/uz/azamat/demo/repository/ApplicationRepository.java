package uz.azamat.demo.repository;

import org.springframework.data.repository.CrudRepository;
import uz.azamat.demo.model.Application;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {
}
