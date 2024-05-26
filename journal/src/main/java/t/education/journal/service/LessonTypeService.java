package t.education.journal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import t.education.journal.entity.LessonType;
import t.education.journal.repository.LessonTypeRepository;

@Service
@RequiredArgsConstructor
public class LessonTypeService {

    private final LessonTypeRepository lessonTypeRepository;

    public LessonType getLessonType(String type) {
        return lessonTypeRepository.findByType(type).orElseThrow(RuntimeException::new);
    }
}
