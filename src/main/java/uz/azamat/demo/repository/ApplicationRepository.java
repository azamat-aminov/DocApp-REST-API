package uz.azamat.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.azamat.demo.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
     Application findById(int id);
     @Modifying
     @Query("delete from Application a where a.id = ?1")
     int deleteById(int id);
}
