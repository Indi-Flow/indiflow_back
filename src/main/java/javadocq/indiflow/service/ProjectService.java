package javadocq.indiflow.service;

import java.util.List;
import javadocq.indiflow.domain.Memo;
import javadocq.indiflow.domain.Project;
import javadocq.indiflow.domain.Task;
import javadocq.indiflow.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Transactional
    public Long join(Project project) {
        projectRepository.save(project);
        return project.getId();
    }

    @Transactional
    public List<Task> getTasks(Long projectId) {
        Project project = projectRepository.findById(projectId);
        if(project == null) {
            throw  new IllegalStateException("Project not found");
        }
        return project.getTasks();
    }

    @Transactional
    public List<Memo> getMemos(Long projectId) {
        Project project = projectRepository.findById(projectId);
        if(project == null) {
            throw  new IllegalStateException("Project not found");
        }
        return project.getMemoList();
    }

    @Transactional
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}
