package javadocq.indiflow.controller;

import jakarta.validation.Valid;
import javadocq.indiflow.domain.SubTask;
import javadocq.indiflow.domain.Task;
import javadocq.indiflow.service.SubTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class SubTaskController {
    private final SubTaskService subTaskService;

    @PostMapping("/project/{projectId}/{taskId}/{subTaskId}")
    public ResponseEntity<SubTask> PostSubTask(@Valid @RequestBody SubTask subTask) {
        SubTask subTask1 = subTaskService.save(subTask);
        return ResponseEntity.ok(subTask1);
    }

    @PostMapping("/project/{projectId}/{taskId}/{subTaskId}/finish")
    public void endSubTask(@PathVariable("subTaskId") Long subTaskId) {
        subTaskService.delete(subTaskId);
    }
}
