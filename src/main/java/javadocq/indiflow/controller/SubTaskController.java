package javadocq.indiflow.controller;

import jakarta.validation.Valid;
import java.util.List;
import javadocq.indiflow.domain.SubTask;
import javadocq.indiflow.domain.Task;
import javadocq.indiflow.service.ProjectService;
import javadocq.indiflow.service.SubTaskService;
import javadocq.indiflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class SubTaskController {
    private final SubTaskService subTaskService;
    private final TaskService taskService;

    @PostMapping("/subTask/{username}/{projectId}/{taskId}/subTask")
    public ResponseEntity<Long> PostSubTask(@PathVariable("taskId") Long taskId,
                                               @Valid @RequestBody SubTask subTask) {

        SubTask savedSubTask = subTaskService.save(subTask, taskId);
        return ResponseEntity.ok(savedSubTask.getId());
    }

    @DeleteMapping("/subTask/{username}/{projectId}/{taskId}/{subTaskId}/finish_subtask")
    public ResponseEntity<String> DeleteSubTask(@PathVariable("subTaskId") Long subTaskId) {

        subTaskService.delete(subTaskId);
        return ResponseEntity.ok("subTask 완료");
    }
}
