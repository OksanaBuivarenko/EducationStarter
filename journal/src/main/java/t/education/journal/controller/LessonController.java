package t.education.journal.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import t.education.journal.dto.request.LessonRq;
import t.education.journal.dto.response.LessonRs;
import t.education.journal.service.LessonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("/")
    public List<LessonRs> getLessons() {
        return lessonService.getLessonsList();
    }

    @GetMapping("/date")
    public List<LessonRs> getLessonsByDate(@RequestParam("from") String from,
                                           @RequestParam("to") String to) {
        return lessonService.getLessonsListByDate(from, to);
    }

    @SneakyThrows
    @GetMapping("/{lessonId}")
    public LessonRs getLessonById(@PathVariable Long lessonId) {
        return lessonService.getLessonRsById(lessonId);
    }

    @PostMapping("/")
    public LessonRs addLesson(@RequestBody LessonRq lessonRq) {
        return lessonService.addLesson(lessonRq);
    }

}
