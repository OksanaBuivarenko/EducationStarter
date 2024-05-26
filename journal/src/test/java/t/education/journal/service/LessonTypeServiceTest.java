package t.education.journal.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import t.education.journal.entity.LessonType;
import t.education.journal.repository.LessonTypeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LessonTypeServiceTest {

    private final LessonTypeRepository lessonTypeRepository =  Mockito.mock(LessonTypeRepository .class);
    private final LessonTypeService lessonTypeService = new LessonTypeService(lessonTypeRepository);
    private LessonType lessonType;

    @BeforeEach
    void setUp() {
        lessonType = new LessonType();
        lessonType.setType("group");
        lessonType.setPrice(350);
    }

    @AfterEach
    void tearDown() {
        lessonType = null;
    }

    @Test
    @DisplayName("Get lesson type")
    void getLessonType() {
        when(lessonTypeRepository.findByType("group")).thenReturn(Optional.of(lessonType));
        LessonType lessonType1 = lessonTypeService.getLessonType("group");
        assertEquals("group", lessonType1.getType());
        assertEquals(350, lessonType1.getPrice());
        verify(lessonTypeRepository, times(1)).findByType("group");
    }
}