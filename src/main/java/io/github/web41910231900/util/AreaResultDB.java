package io.github.web41910231900.util;

import io.github.web41910231900.model.CheckArea;
import io.github.web41910231900.model.entity.CheckAreaEntity;
import io.github.web41910231900.model.entity.repository.CheckAreaRepository;
import io.github.web41910231900.model.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.ZoneId;
import java.util.List;

@Component
public class AreaResultDB {

    private final UserRepository userRepository;
    private final CheckAreaRepository areaRepository;

    @Autowired
    public AreaResultDB(final UserRepository userRepository,
                        final CheckAreaRepository areaRepository) {
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
    }

    public void pushToDB(CheckArea area, Principal username) {
        final CheckAreaEntity entity = new CheckAreaEntity();
        entity.setX(area.getRequest().getX());
        entity.setY(area.getRequest().getY());
        entity.setR(area.getRequest().getR());
        entity.setResult(area.isResult());
        entity.setExecutedAt(area.getExecutedAt().atZone(ZoneId.systemDefault()).toLocalDateTime());
        entity.setExecutionTime(area.getExecutionTime());
        entity.setOwnerID(userRepository.findByUsername(username.getName()));
        areaRepository.save(entity);
    }

    public List<CheckArea> getAllByUsername(Principal principal) {
        List<CheckAreaEntity> resultEntity = areaRepository
                .findAllByOwnerID(userRepository.findByUsername(principal.getName()));

        return resultEntity.stream().map(CheckArea::fromEntity).toList();
    }
}
