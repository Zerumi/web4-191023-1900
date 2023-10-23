package io.github.web41910231900.controller;

import io.github.web41910231900.model.CheckArea;
import io.github.web41910231900.model.request.CheckHitRequestDTO;
import io.github.web41910231900.util.AreaResultChecker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/check-hit")
public class AreaCheckController {
    @PostMapping
    public ResponseEntity<CheckArea> newCheckResult(@RequestBody CheckHitRequestDTO rq) {
        var result = new CheckArea();
        result.setRequest(rq);
        long startTime = System.nanoTime();
        result.setResult(AreaResultChecker.getResult(rq.getX(), rq.getY(), rq.getR()));
        long endTime = System.nanoTime();
        result.setExecutedAt(LocalDateTime.now());
        result.setExecutionTime(endTime - startTime);
        return ResponseEntity.ok(result);
    }
}
