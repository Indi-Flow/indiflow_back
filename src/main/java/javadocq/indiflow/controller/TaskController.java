package javadocq.indiflow.controller;

import jakarta.validation.Valid;
import java.util.List;
import javadocq.indiflow.domain.Project;
import javadocq.indiflow.domain.SubTask;
import javadocq.indiflow.domain.Task;

import javadocq.indiflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/project/{projectId}/{taskId}")
    public ResponseEntity<List<SubTask>> getSubTask(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId) {
        List<SubTask> subTaskList = taskService.getSubTasks(projectId, taskId);
        return ResponseEntity.ok(subTaskList);
    }

    @PostMapping("/project/task")
    public ResponseEntity<String> PostProject(@Valid @RequestBody Task task) {
        Long saveTaskId = taskService.join(task);
        return ResponseEntity.ok(saveTaskId.toString());
    }

    @PostMapping("/project/{projectId}/{taskId}/finish")
    public void endTask(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId) {
        Task task = taskService.getTask(projectId, taskId);
        if(task == null) {
            throw  new IllegalStateException("Task not found");
        }
        taskService.deleteTask(taskId);
    }
}
