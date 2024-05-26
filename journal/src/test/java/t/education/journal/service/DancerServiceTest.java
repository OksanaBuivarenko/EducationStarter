package t.education.journal.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import t.education.journal.dto.request.DancerRq;
import t.education.journal.dto.response.DancerRs;
import t.education.journal.dto.response.PaymentRs;
import t.education.journal.entity.DanceGroup;
import t.education.journal.entity.Dancer;
import t.education.journal.entity.Lesson;
import t.education.journal.entity.LessonType;
import t.education.journal.repository.DancerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DancerServiceTest {

    private final DancerRepository dancerRepository = Mockito.mock(DancerRepository.class);
    private final LessonService lessonService = Mockito.mock(LessonService.class);
    private final DancerService dancerService = new DancerService(dancerRepository, lessonService);
    private Dancer dancer1;
    private Dancer dancer2;
    private List<Dancer> dancerList;

    @BeforeEach
    void setUp() {
        dancer1 = new Dancer();
        dancer1.setFirstName("Anna");
        dancer1.setLastName("Ivanova");
        dancer1.setBirthday(LocalDate.now().minusYears(10));
        dancer1.setPhone("80000000000");
        dancer1.setId(1L);

        dancer2 = new Dancer();
        dancer2.setFirstName("Olga");
        dancer2.setLastName("Reznikova");
        dancer2.setBirthday(LocalDate.now().minusYears(5));
        dancer2.setPhone("81111111111");
        dancer2.setId(2L);

        dancerList = new ArrayList<>();
        dancerList.add(dancer1);
        dancerList.add(dancer2);
    }

    @AfterEach
    void tearDown() {
        dancer1 = null;
        dancer2 = null;
    }

    @Test
    @DisplayName("Get dancer list")
    void getDancerList() {
        when(dancerRepository.findAll()).thenReturn(dancerList);
        List<DancerRs> dancerRsList = dancerService.getDancerList();
        assertEquals(2, dancerRsList.size());
        verify(dancerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Get dancerRs by id")
    void getDancerRsById() {
        when(dancerRepository.findById(1L)).thenReturn(Optional.of(dancer1));
        DancerRs dancerRs = dancerService.getDancerRsById(1L);
        assertEquals("Anna", dancerRs.getFirstName());
        assertEquals("Ivanova", dancerRs.getLastName());
        verify(dancerRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Add dancer")
    void addDancer() {
        DancerRq dancerRq = DancerRq.builder().firstName("Ivan").lastName("Ivanov")
                .birthday(LocalDate.now().minusYears(7)).phone("83333333333").build();
        dancerService.addDancer(dancerRq);
        verify(dancerRepository, times(1)).save(any(Dancer.class));
    }

    @Test
    @DisplayName("Get payment by dancer id")
    void getPaymentByDancerId() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setDate(LocalDate.now());
        LessonType lessonType = new LessonType();
        lessonType.setType("group");
        lessonType.setPrice(350);
        lesson.setLessonType(lessonType);
        lesson.setDanceGroup(DanceGroup.JUNIOR);
        dancer1.getLessons().add(lesson);
        when(dancerRepository.findById(1L)).thenReturn(Optional.of(dancer1));
        PaymentRs paymentRs = dancerService.getPaymentByDancerId(1L, LocalDate.now().minusDays(2).toString(),
                LocalDate.now().plusDays(2).toString());
        assertEquals(350, paymentRs.getSum());
    }

    @Test
    @DisplayName("Get dancer by id")
    void getDancerById() {
        when(dancerRepository.findById(1L)).thenReturn(Optional.of(dancer1));
        Dancer dancer = dancerService.getDancerById(1L);
        assertEquals("Anna", dancer.getFirstName());
        assertEquals("Ivanova", dancer.getLastName());
        verify(dancerRepository, times(1)).findById(1L);
    }

    @Test
    void addLessonToDancer() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setDanceGroup(DanceGroup.JUNIOR);
        when(dancerRepository.findById(1L)).thenReturn(Optional.of(dancer1));
        when(lessonService.getLessonById(1L)).thenReturn(lesson);
        DancerRs dancerRs = dancerService.addLessonToDancer(1L, 1L);
        assertEquals("Anna", dancerRs.getFirstName());
        assertEquals("Ivanova", dancerRs.getLastName());
        verify(dancerRepository, times(1)).findById(1L);
        verify(dancerRepository, times(1)).save(dancer1);
    }
}