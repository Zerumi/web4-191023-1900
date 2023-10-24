package io.github.web41910231900.model.entity.repository;

import io.github.web41910231900.model.entity.CheckAreaEntity;
import io.github.web41910231900.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckAreaRepository extends JpaRepository<CheckAreaEntity, Long> {

    List<CheckAreaEntity> findAllByOwnerID(UserEntity entity);

    void deleteAllByOwnerID(UserEntity entity);
}
