package t.education.journal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import t.education.journal.dto.request.DancerRq;
import t.education.journal.dto.response.DancerRs;
import t.education.journal.entity.DanceGroup;
import t.education.journal.entity.Dancer;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DancerMapper {
    DancerMapper INSTANCE = getMapper(DancerMapper.class);

    @Mapping(target = "danceGroup", source = "danceGroup")
    Dancer toEntity(DancerRq dancerRq, DanceGroup danceGroup);

    DancerRs toDto(Dancer dancer);
}
