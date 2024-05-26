package t.education.journal.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LessonRq {

    private LocalDate date;

    private String danceGroup;

    private String lessonType;
}
