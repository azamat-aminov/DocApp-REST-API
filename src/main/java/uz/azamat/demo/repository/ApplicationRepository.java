package uz.azamat.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.azamat.demo.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
     Application findById(int id);
}
