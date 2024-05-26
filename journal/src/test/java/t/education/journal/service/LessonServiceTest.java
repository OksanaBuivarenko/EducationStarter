package t.education.journal.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import t.education.journal.dto.request.LessonRq;
import t.education.journal.dto.response.LessonRs;
import t.education.journal.entity.DanceGroup;
import t.education.journal.entity.Dancer;
import t.education.journal.entity.Lesson;
import t.education.journal.entity.LessonType;
import t.education.journal.repository.LessonRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LessonServiceTest {
    private final LessonRepository lessonRepository = Mockito.mock(LessonRepository.class);
    private final LessonTypeService lessonTypeService = Mockito.mock(LessonTypeService.class);
    private final LessonService lessonService = new LessonService(lessonRepository, lessonTypeService);
    private Lesson lesson;
    private LessonType lessonType;
    private List<Lesson> lessonList;

    @BeforeEach
    void setUp() {
        lessonType = new LessonType();
        lessonType.setType("group");
        lessonType.setPrice(350);

        lesson = new Lesson();
        lesson.setId(1L);
        lesson.setDate(LocalDate.now());
        lesson.setLessonType(lessonType);
        lesson.setDanceGroup(DanceGroup.JUNIOR);

        lessonList = new ArrayList<>();
        lessonList.add(lesson);
    }

    @AfterEach
    void tearDown() {
        lessonList = null;
        lesson = null;
        lessonType = null;
    }

    @Test
    @DisplayName("Get lesson list")
    void getLessonsList() {
        when(lessonRepository.findAll()).thenReturn(lessonList);
        List<LessonRs> lessonRsList = lessonService.getLessonsList();
        assertEquals(1,lessonRsList.size());
        verify(lessonRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Get lesson list by date")
    void getLessonsListByDate() {
        when(lessonRepository.findByDateBetweenOrderByDateDesc(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(2))).thenReturn(lessonList);
        List<LessonRs> lessonRsList = lessonService.getLessonsListByDate(LocalDate.now().minusDays(2).toString(),
                LocalDate.now().plusDays(2).toString());
        assertEquals(1,lessonRsList.size());
        verify(lessonRepository, times(1)).findByDateBetweenOrderByDateDesc(LocalDate.now()
                        .minusDays(2), LocalDate.now().plusDays(2));

    }

    @Test
    @DisplayName("Get lessonRs by id")
    void getLessonRsById() {
        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
        LessonRs lessonRs = lessonService.getLessonRsById(1L);
        assertEquals("group", lessonRs.getLessonType());
        assertEquals(DanceGroup.JUNIOR.toString(), lessonRs.getDanceGroup());
        verify(lessonRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Get lesson by id")
    void getLessonById() {
        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
        Lesson lesson = lessonService.getLessonById(1L);
        assertEquals("group", lesson.getLessonType().getType());
        assertEquals(DanceGroup.JUNIOR, lesson.getDanceGroup());
        verify(lessonRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Add lesson")
    void addLesson() {
        LessonRq lessonRq = LessonRq.builder().lessonType(lessonType.getType()).danceGroup(DanceGroup.JUNIOR.toString())
                        .date(LocalDate.now()).build();
        lessonService.addLesson(lessonRq);
        verify(lessonRepository, times(1)).save(any(Lesson.class));
    }

    @Test
    @DisplayName("Add dancer")
    void addDancer() {
        Dancer dancer = new Dancer();
        dancer.setFirstName("Anna");
        dancer.setLastName("Ivanova");
        dancer.setBirthday(LocalDate.now().minusYears(10));
        dancer.setPhone("80000000000");
        dancer.setId(1L);
        lessonService.addDancer(dancer, lesson);
        verify(lessonRepository, times(1)).save(any(Lesson.class));
    }
}