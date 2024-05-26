package t.education.journal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import t.education.journal.entity.Dancer;
import t.education.journal.entity.Lesson;
import t.education.journal.entity.LessonType;
import t.education.journal.exception.NotFoundException;
import t.education.journal.repository.LessonRepository;
import t.education.journal.dto.request.LessonRq;
import t.education.journal.dto.response.LessonRs;
import t.education.journal.mapper.LessonMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final LessonTypeService lessonTypeService;

    public List<LessonRs> getLessonsList() {
        return lessonRepository.findAll().stream().map(lesson ->
                LessonMapper.INSTANCE.toDto(lesson, lesson.getLessonType().getType()))
                .collect(Collectors.toList());
    }

    public List<LessonRs> getLessonsListByDate(String from, String to) {
        return lessonRepository.findByDateBetweenOrderByDateDesc(LocalDate.parse(from), LocalDate.parse(to))
                .stream().map(lesson ->LessonMapper.INSTANCE.toDto(lesson, lesson.getLessonType().getType()))
                .collect(Collectors.toList());
    }

    public LessonRs getLessonRsById(Long lessonId) {
        Lesson lesson = getLessonById(lessonId);
        return LessonMapper.INSTANCE.toDto(lesson, lesson.getLessonType().getType());
    }

    public Lesson getLessonById(Long lessonId) {
        return lessonRepository.findById(lessonId).orElseThrow(() -> new NotFoundException(lessonId));
    }

    public LessonRs addLesson(LessonRq lessonRq) {
        LessonType lessonType = lessonTypeService.getLessonType(lessonRq.getLessonType());
        return LessonMapper.INSTANCE.toDto(lessonRepository.save(LessonMapper.INSTANCE.toEntity(lessonRq, lessonType)),
                lessonRq.getLessonType());
    }

    public void addDancer(Dancer dancer, Lesson lesson) {
        lesson.getDancers().add(dancer);
        lessonRepository.save(lesson);
    }
}
