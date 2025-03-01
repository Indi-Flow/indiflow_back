package javadocq.indiflow.service;

import java.util.List;
import javadocq.indiflow.domain.Project;
import javadocq.indiflow.domain.User;
import javadocq.indiflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
      private final UserRepository userRepository;

      @Transactional
      public Long join(User user) {
          validateUser(user);
          userRepository.save(user);
          return user.getId();
      }

          private void validateUser(User user) {
          User findUser = userRepository.findByUsername(user.getUsername());
          if(findUser != null) {
              throw new IllegalStateException("이미 존재하는 회원입니다.");
          }
      }

      @Transactional
      public List<Project> getUserProjects(String username) {
          User user = userRepository.findByUsername(username);
          if(user == null) {
              throw  new IllegalStateException("유저를 찾을 수 없습니다.");
          }
          return user.getProjects();
      }
}
