package t.education.journal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import t.education.journal.dto.request.DancerRq;
import t.education.journal.dto.response.DancerRs;
import t.education.journal.dto.response.PaymentRs;
import t.education.journal.entity.DanceGroup;
import t.education.journal.entity.Dancer;
import t.education.journal.entity.Lesson;
import t.education.journal.exception.NotFoundException;
import t.education.journal.mapper.DancerMapper;
import t.education.journal.repository.DancerRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DancerService {

    private final DancerRepository dancerRepository;
    private final LessonService lessonService;

    public List<DancerRs> getDancerList() {
        return dancerRepository.findAll().stream().map(DancerMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public DancerRs getDancerRsById(Long dancerId) {
        return DancerMapper.INSTANCE.toDto(getDancerById(dancerId));
    }

    public DancerRs addDancer(DancerRq dancerRq) {
        return DancerMapper.INSTANCE.toDto(dancerRepository.save(DancerMapper.INSTANCE.toEntity(dancerRq,
                getGroup(dancerRq.getBirthday()))));
    }

    private DanceGroup getGroup(LocalDate birthday) {
        int age = Period.between(birthday, LocalDate.now()).getYears();
        if (age < 5) {
            return DanceGroup.BABY;
        }
        if (age > 5 && age < 8) {
            return DanceGroup.MINI;
        }
        if (age > 8 && age < 14) {
            return DanceGroup.JUNIOR;
        } else {
            return DanceGroup.ADULT;
        }
    }

    public PaymentRs getPaymentByDancerId(Long dancerId, String from, String to) {
        Dancer dancer = getDancerById(dancerId);
        int sum = 0;

        List<Lesson> lessons = dancer.getLessons().stream().filter(lesson -> lesson.getDate().isBefore(LocalDate.parse(to)) &&
                lesson.getDate().isAfter(LocalDate.parse(from))).toList();
        for (Lesson lesson : lessons) {
            sum += lesson.getLessonType().getPrice();
        }
        return PaymentRs.builder()
                .dancerFirstName(dancer.getFirstName())
                .dancerLastName(dancer.getLastName())
                .from(LocalDate.parse(from))
                .to(LocalDate.parse(to))
                .sum(sum)
                .build();
    }

    public Dancer getDancerById(Long dancerId) {
        return dancerRepository.findById(dancerId).orElseThrow(() -> new NotFoundException(dancerId));
    }

    public DancerRs addLessonToDancer(Long dancerId, Long lessonId) {
        Dancer dancer = getDancerById(dancerId);
        Lesson lesson = lessonService.getLessonById(lessonId);
        dancer.getLessons().add(lesson);
        dancerRepository.save(dancer);
        lessonService.addDancer(dancer, lesson);
        return DancerMapper.INSTANCE.toDto(dancer);
    }
}
