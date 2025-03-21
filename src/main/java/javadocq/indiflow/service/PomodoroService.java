package javadocq.indiflow.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javadocq.indiflow.DTO.PomodoroDTO;
import javadocq.indiflow.domain.Pomodoro;
import javadocq.indiflow.domain.User;
import javadocq.indiflow.repository.PomodoroRepository;
import javadocq.indiflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PomodoroService {

    private final PomodoroRepository pomodoroRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(Pomodoro pomodoro) {
        pomodoroRepository.save(pomodoro);
    }

    public List<Pomodoro> getAllPomodoroByUsername(String username) {
        return pomodoroRepository.findAllByUsername(username);
    }

    @Transactional
    public void addPomodoroForUser(String username) {
        // 사용자 조회
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        // 새로운 Pomodoro 객체 생성
        Pomodoro pomodoro = new Pomodoro();
        pomodoro.setUser(user);
        pomodoro.setCompleted_at(LocalDateTime.now());  // 현재 시간으로 설정

        // d_count, t_count 증가
        pomodoro.incrementDCount();
        pomodoro.incrementTCount();

        // Pomodoro 객체를 User의 pomodoros에 추가
        user.getPomodoros().add(pomodoro);

        // 저장
        pomodoroRepository.save(pomodoro);
    }

    public PomodoroDTO getPomodoroStatsByUsername(String username) {
        List<Pomodoro> allPomodoros = getAllPomodoroByUsername(username);

        long todayCount = allPomodoros.stream()
                .filter(p -> p.getCompleted_at().toLocalDate().equals(LocalDate.now()))
                .count();

        int totalCount = allPomodoros.size();

        return new PomodoroDTO( (int) todayCount, totalCount);
    }
}
