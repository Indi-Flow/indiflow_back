package javadocq.indiflow.controller;

import javadocq.indiflow.DTO.PomodoroDTO;
import javadocq.indiflow.domain.User;
import javadocq.indiflow.repository.UserRepository;
import javadocq.indiflow.service.PomodoroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "https://indiflow-front.vercel.app/")
public class PomodoroController {
    private final PomodoroService pomodoroService;
    private final UserRepository userRepository;

    @GetMapping("/pomodoro/stats/{username}")
    public ResponseEntity<PomodoroDTO> getPomodoroData(@PathVariable String username) {
        return ResponseEntity.ok(pomodoroService.getPomodoroStatsByUsername(username));
    }

    @PostMapping("/pomodoro/add/{username}")
    public ResponseEntity<String> addPomodoro(@PathVariable String username) {
        pomodoroService.addPomodoroForUser(username);
        return ResponseEntity.ok("Pomodoro added");
    }
}
