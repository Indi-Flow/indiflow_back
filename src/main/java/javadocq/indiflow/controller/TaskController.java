package javadocq.indiflow.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import javadocq.indiflow.DTO.SubTaskDTO;
import javadocq.indiflow.domain.Project;
import javadocq.indiflow.domain.SubTask;
import javadocq.indiflow.domain.Task;

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
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/task/{username}/{projectId}/{taskId}/subTask_list")
    public ResponseEntity<List<SubTaskDTO>> getSubTask(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId) {
        List<SubTask> subTaskList = taskService.getSubTasks(projectId, taskId);
        List<SubTaskDTO> subTaskDTOS = subTaskList.stream().map(SubTaskDTO::new).toList();
        return ResponseEntity.ok(subTaskDTOS);
    }

    @PostMapping("/task/{username}/{projectId}/task")
    public ResponseEntity<String> PostTask(@Valid @RequestBody Task task, @PathVariable("projectId") Long projectId) {
        Long saveTaskId = taskService.join(projectId, task);
        return ResponseEntity.ok(saveTaskId.toString());
    }

    @DeleteMapping("/task/{username}/{projectId}/{taskId}/finish_task")
    public ResponseEntity<String> DeleteTask(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId) {
        Task task = taskService.getTask(projectId, taskId);
        if(task == null) {
            throw  new IllegalStateException("Task not found");
        }
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task 완료");
    }
}
