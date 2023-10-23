package io.github.web41910231900.controller;

import io.github.web41910231900.model.CheckArea;
import io.github.web41910231900.model.request.CheckHitRequestDTO;
import io.github.web41910231900.util.AreaResultChecker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("/check-hit")
public class AreaCheckController {
    @PostMapping
    public ResponseEntity<CheckArea> newCheckResult(Principal principal,
                                                    @RequestBody CheckHitRequestDTO rq) {

        String user = principal.getName();

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
