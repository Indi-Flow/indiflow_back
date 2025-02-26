package javadocq.indiflow.controller;

import jakarta.validation.Valid;
import javadocq.indiflow.domain.User;
import javadocq.indiflow.repository.UserRepository;
import javadocq.indiflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/user/sign_up")
    public ResponseEntity<User> signUp(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException("회원가입 실패");
        } else {
            userService.join(user);
            return ResponseEntity.ok(user);  // JSON 응답 반환
        }
    }

    @PostMapping("/user/sign_in")
    public ResponseEntity<Long> signIn(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalStateException("로그인 실패");
        } else {
            User saveUser = userRepository.findByUsername(user.getUsername());
            if(saveUser == null) {
                throw new IllegalStateException("회원이 존재하지 않습니다.");
            } else {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (!passwordEncoder.matches(user.getPassword(), saveUser.getPassword())) {
                    throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
                }
                return ResponseEntity.ok(saveUser.getId());
            }
        }
    }
}
