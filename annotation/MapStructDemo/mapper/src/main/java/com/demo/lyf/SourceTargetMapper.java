package com.demo.lyf;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SourceTargetMapper {

    SourceTargetMapper INSTANCE = Mappers.getMapper(SourceTargetMapper.class);

    @Mappings({
            @Mapping(source = "qax", target = "baz"),
            @Mapping(source = "baz", target = "qax")
    })
    Target sourceToTarget(Source source);

    @InheritInverseConfiguration
    Source targetToSource(Target target);
}
