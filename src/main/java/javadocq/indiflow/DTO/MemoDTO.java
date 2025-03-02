package javadocq.indiflow.DTO;

import java.time.LocalDateTime;
import javadocq.indiflow.domain.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MemoDTO {
    private final Long id;
    private final String content;
    private final LocalDateTime created_at;

    public MemoDTO(Memo memo) {
        this.id = memo.getId();
        this.content = memo.getContent();
        this.created_at = memo.getCreated_at();
    }

}
