package javadocq.indiflow.DTO;

import java.util.List;
import javadocq.indiflow.domain.Pomodoro;
import lombok.Getter;

@Getter
public class PomodoroDTO {
    private final int d_count;
    private final int t_count;

    public PomodoroDTO(int d_count, int t_count) {
        this.d_count = d_count;
        this.t_count = t_count;
    }
}
