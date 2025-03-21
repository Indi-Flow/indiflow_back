package javadocq.indiflow.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pomodoro {
    @Id @GeneratedValue
    @Column(name= "pomodoro_id")
    private Long id;

    private int d_count;
    private int t_count;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completed_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private User user;

    public void incrementDCount() {
        this.d_count++;
    }

    // t_count 증가
    public void incrementTCount() {
        this.t_count++;
    }


}
