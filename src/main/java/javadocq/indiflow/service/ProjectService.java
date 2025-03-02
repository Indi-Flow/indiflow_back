package javadocq.indiflow.service;

import java.util.List;
import java.util.stream.Collectors;
import javadocq.indiflow.DTO.TaskWithSubTasksDTO;
import javadocq.indiflow.domain.Memo;
import javadocq.indiflow.domain.Project;
import javadocq.indiflow.domain.Task;
import javadocq.indiflow.domain.User;
import javadocq.indiflow.repository.MemoRepository;
import javadocq.indiflow.repository.ProjectRepository;
import javadocq.indiflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final MemoRepository memoRepository;

    @Transactional
    public Long join(String username, Project project) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        project.setUser(user);  // 유저와 프로젝트 연결
        projectRepository.save(project);
        return project.getId();
    }

    @Transactional
    public List<TaskWithSubTasksDTO> getProjectTasksWithSubTasks(String username, Long projectId) {
        Project project = projectRepository.findById(projectId);
        if (!project.getUser().getUsername().equals(username)) {
            throw new IllegalStateException("User does not have access to this project");
        }

        return project.getTasks().stream()
                .map(TaskWithSubTasksDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Task> getTasks(String username, Long projectId) {
        Project project = projectRepository.findById(projectId);

        if (!project.getUser().getUsername().equals(username)) {
            throw new IllegalStateException("User does not have access to this project");
        }

        return project.getTasks();
    }

    @Transactional
    public List<Memo> getMemos(String username, Long projectId) {
        Project project = projectRepository.findById(projectId);

        if (!project.getUser().getUsername().equals(username)) {
            throw new IllegalStateException("User does not have access to this project");
        }

        return project.getMemoList();
    }

    @Transactional
    public Long addMemo(String username, Long projectId, Memo memo) {
        Project project = projectRepository.findById(projectId);
        if (!project.getUser().getUsername().equals(username)) {
            throw new IllegalStateException("User does not have access to this project");
        }

        memo.setProject(project);
        memoRepository.save(memo);
        return memo.getId();
    }

    @Transactional
    public void deleteProject(String username, Long projectId) {
        Project project = projectRepository.findById(projectId);

        if (!project.getUser().getUsername().equals(username)) {
            throw new IllegalStateException("User does not have access to this project");
        }

        projectRepository.deleteById(projectId);
    }
}
