package com.minhdubai.Giftback.mapper.implement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minhdubai.Giftback.domain.dto.GroupDto;
import com.minhdubai.Giftback.domain.entity.Group;
import com.minhdubai.Giftback.mapper.Mapper;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GroupMapper implements Mapper<Group, GroupDto> {
    private final ModelMapper modelMapper;

    @PostConstruct
    public void setupMapper() {
        modelMapper.typeMap(Group.class, GroupDto.class)
                .addMappings(mapper -> mapper.skip(GroupDto::setUsers));
    }

    @Override
    public GroupDto mapTo(Group group) {
        return modelMapper.map(group, GroupDto.class);
    }

    @Override
    public Group mapFrom(GroupDto groupDto) {
        return modelMapper.map(groupDto, Group.class);
    }
}
