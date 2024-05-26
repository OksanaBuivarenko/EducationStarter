package t.education.journal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import t.education.journal.dto.request.LessonRq;
import t.education.journal.dto.response.LessonRs;
import t.education.journal.entity.Lesson;
import t.education.journal.entity.LessonType;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LessonMapper {
    LessonMapper INSTANCE = getMapper(LessonMapper.class);

    @Mapping(target = "lessonType", source = "lessonType")
    Lesson toEntity(LessonRq lessonRq, LessonType lessonType);

    @Mapping(target = "lessonType", source = "lessonType")
    LessonRs toDto(Lesson lesson, String lessonType);
}
