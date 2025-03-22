package javadocq.indiflow.controller;

import jakarta.validation.Valid;
import java.util.List;
import javadocq.indiflow.DTO.MemoDTO;
import javadocq.indiflow.DTO.ProjectDTO;
import javadocq.indiflow.DTO.TaskDTO;
import javadocq.indiflow.DTO.TaskWithSubTasksDTO;
import javadocq.indiflow.domain.Memo;
import javadocq.indiflow.domain.Project;
import javadocq.indiflow.domain.Task;
import javadocq.indiflow.repository.ProjectRepository;
import javadocq.indiflow.service.ProjectService;
import javadocq.indiflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "https://indiflow-front.vercel.app/")
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;
    private final ProjectRepository projectRepository;


    @GetMapping("/projects/{username}")
    public ResponseEntity<List<ProjectDTO>> getProjects(@PathVariable String username) {
        List<Project> projects = userService.getUserProjects(username);
        List<ProjectDTO> projectDTOs = projects.stream().map(ProjectDTO::new).toList();
        return ResponseEntity.ok(projectDTOs);
    }

    @PostMapping("/project/{username}")
    public ResponseEntity<ProjectDTO> PostProject(@PathVariable String username, @Valid @RequestBody Project project) {
        Long savedId = projectService.join(username, project);
        Project savedProject = projectRepository.findById(savedId);
        ProjectDTO projectDTO = new ProjectDTO(savedProject);
        return ResponseEntity.ok(projectDTO);
    }

    @GetMapping("/project/{username}/{projectId}/tasks_with_subtasks")
    public ResponseEntity<List<TaskWithSubTasksDTO>> getProjectTasksWithSubTasks(@PathVariable String username, @PathVariable Long projectId) {
        List<TaskWithSubTasksDTO> taskDTOs = projectService.getProjectTasksWithSubTasks(username, projectId);
        return ResponseEntity.ok(taskDTOs);
    }

    @GetMapping("/project/{username}/{projectId}/task_list")
    public ResponseEntity<List<TaskDTO>> getProject(@PathVariable("projectId") Long projectId, @PathVariable String username) {
        List<Task> tasks = projectService.getTasks(username, projectId);
        List<TaskDTO> taskDTOs = tasks.stream().map(TaskDTO::new).toList();
        return ResponseEntity.ok(taskDTOs);
    }

    @GetMapping("/project/{username}/{projectId}/memo")
    public ResponseEntity<List<MemoDTO>> getMemo(@PathVariable("projectId") Long projectId, @PathVariable String username) {
        List<Memo> memos = projectService.getMemos(username, projectId);
        List<MemoDTO> memoDTOs = memos.stream().map(MemoDTO::new).toList();
        return ResponseEntity.ok(memoDTOs);
    }

    @PostMapping("/project/{username}/{projectId}/memo_add")
    public ResponseEntity<String> PostMemo(@PathVariable String username, @PathVariable Long projectId, @Valid @RequestBody Memo memo) {
        Long saveMemoId = projectService.addMemo(username, projectId, memo);
        return ResponseEntity.ok(saveMemoId.toString());
    }

    @DeleteMapping("/project/{username}/{projectId}/finish_project")
    public ResponseEntity<String> DeleteProject(@PathVariable("projectId") Long projectId, @PathVariable String username) {
        projectService.deleteProject(username, projectId);

        return ResponseEntity.ok("Project 완료");
    }
}
