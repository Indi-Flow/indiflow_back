package javadocq.indiflow.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Getter
public class Memo {

    @Id @GeneratedValue
    @Column(name = "memo_id")
    private Long id;
    @Column(nullable = false)
    private String content;
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @PrePersist
    public void onCreateTime() {
        this.created_at = LocalDateTime.now();
    }

}
