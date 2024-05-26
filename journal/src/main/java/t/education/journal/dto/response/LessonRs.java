package t.education.journal.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LessonRs {

    private LocalDate date;

    private String danceGroup;

    private String lessonType;
}
