package com.minhdubai.Giftback.service;

import org.springframework.stereotype.Service;

import com.minhdubai.Giftback.domain.dto.GroupDto;
import com.minhdubai.Giftback.domain.entity.Group;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.repository.GroupRepository;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final Mapper<Group, GroupDto> groupMapper;

    public ResponseDto getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        List<GroupDto> groupDtos = groups.stream()
                .map(groupMapper::mapTo)
                .toList();
        return ResponseDto.builder()
                .status(200)
                .message("Groups retrieved successfully")
                .data(groupDtos)
                .build();
    }

    public ResponseDto createGroup(GroupDto groupDto) {
        Group group = groupMapper.mapFrom(groupDto);
        Group savedGroup = groupRepository.save(group);
        return ResponseDto.builder()
                .status(201)
                .message("Group created successfully")
                .data(groupMapper.mapTo(savedGroup))
                .build();
    }

    public ResponseDto updateGroup(Integer id, GroupDto groupDto) {
        Optional<Group> existingGroup = groupRepository.findById(id);
        if (existingGroup.isPresent()) {
            Group groupToUpdate = groupMapper.mapFrom(groupDto);
            groupToUpdate.setId(id);
            Group updatedGroup = groupRepository.save(groupToUpdate);
            return ResponseDto.builder()
                    .status(200)
                    .message("Group updated successfully")
                    .data(groupMapper.mapTo(updatedGroup))
                    .build();
        } else {
            return ResponseDto.builder()
                    .status(404)
                    .message("Group not found")
                    .build();
        }
    }

    public ResponseDto deleteGroup(Integer id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return ResponseDto.builder()
                    .status(200)
                    .message("Group deleted successfully")
                    .build();
        } else {
            return ResponseDto.builder()
                    .status(404)
                    .message("Group not found")
                    .build();
        }
    }
}
