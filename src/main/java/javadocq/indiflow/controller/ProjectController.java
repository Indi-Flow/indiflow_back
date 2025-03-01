package javadocq.indiflow.controller;

import jakarta.validation.Valid;
import java.util.List;
import javadocq.indiflow.domain.Memo;
import javadocq.indiflow.domain.Project;
import javadocq.indiflow.domain.Task;
import javadocq.indiflow.domain.User;
import javadocq.indiflow.service.ProjectService;
import javadocq.indiflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;


    @GetMapping("/projects/{username}")
    public ResponseEntity<List<Project>> getProjects(@PathVariable String username) {
        List<Project> projects = userService.getUserProjects(username);
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/projects")
    public ResponseEntity<String> PostProject(@Valid @RequestBody Project project) {
        Long saveProjectId = projectService.join(project);
        return ResponseEntity.ok(saveProjectId.toString());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getProject(@PathVariable("projectId") Long projectId) {
        List<Task> tasks = projectService.getTasks(projectId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/project/{projectId}/memo")
    public ResponseEntity<List<Memo>> getMemo(@PathVariable("projectId") Long projectId) {
        List<Memo> memos = projectService.getMemos(projectId);
        return ResponseEntity.ok(memos);
    }

    @PostMapping("/project/{projectId}/finish")
    public void endTask(@PathVariable("projectId") Long projectId) {
        projectService.deleteProject(projectId);
    }
}
