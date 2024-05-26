package t.education.journal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "dance_group")
    @Enumerated(EnumType.STRING)
    private DanceGroup danceGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    private LessonType lessonType;

    @ManyToMany(mappedBy = "lessons")
    private Set<Dancer> dancers = new HashSet<>();
}
