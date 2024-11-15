package com.minhdubai.Giftback.service;

import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Service;

import com.minhdubai.Giftback.domain.dto.GroupDto;
import com.minhdubai.Giftback.domain.dto.UserDto;
import com.minhdubai.Giftback.domain.entity.Group;
import com.minhdubai.Giftback.domain.entity.User;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.repository.GroupRepository;
import com.minhdubai.Giftback.repository.UserRepository;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final Mapper<Group, GroupDto> groupMapper;
    private final Mapper<User, UserDto> userMapper;

    public ResponseDto getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        List<GroupDto> groupDtos = groups.stream()
                .map(group -> {
                    GroupDto groupDto = groupMapper.mapTo(group);
                    groupDto.setMemberCount(group.getUsers().size());
                    return groupDto;
                })
                .toList();
        return ResponseDto.builder()
                .status(200)
                .message("Groups retrieved successfully")
                .data(groupDtos)
                .build();
    }

    public ResponseDto createGroup(GroupDto groupDto) {
        Group group = groupMapper.mapFrom(groupDto);
        User owner = userRepository.findById(groupDto.getOwner().getId()).orElse(null);
        Group savedGroup = groupRepository.save(group);
        if (owner != null) {
            owner.setGroup(savedGroup);
            userRepository.save(owner);
        }
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

    public ResponseDto joinGroup(Integer userId, Integer groupId) {
        Group existingGroup = groupRepository.findById(groupId).orElse(null);
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingGroup != null && existingUser != null) {
            existingUser.setGroup(existingGroup);
            userRepository.save(existingUser);
            return ResponseDto.builder()
                    .status(204)
                    .message("Joined successfully")
                    .build();
        } else {
            return ResponseDto.builder()
                    .status(404)
                    .message("Group or user not found")
                    .build();
        }
    }

    public ResponseDto leaveGroup(Integer userId) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setGroup(null);
            userRepository.save(existingUser);
            return ResponseDto.builder()
                    .status(200)
                    .message("Leave group successfully")
                    .build();
        } else {
            return ResponseDto.builder()
                    .status(404)
                    .message("User not found")
                    .build();
        }
    }

    public ResponseDto getGroupMembers(Integer id) {
        Group existingGroup = groupRepository.findById(id).orElse(null);
        if (existingGroup != null) {
            List<UserDto> members = existingGroup.getUsers()
                    .stream()
                    .map(userMapper::mapTo)
                    .toList();
            return ResponseDto.builder()
                    .status(200)
                    .message("Got members successfully")
                    .data(members)
                    .build();
        } else {
            return ResponseDto.builder()
                    .status(404)
                    .message("Group not found")
                    .build();
        }
    }

    public ResponseDto getGroupById(Integer id) {
        Group existingGroup = groupRepository.findById(id).orElse(null);
        if (existingGroup != null) {
            GroupDto groupDto = groupMapper.mapTo(existingGroup);
            groupDto.setMemberCount(existingGroup.getUsers().size());
            return ResponseDto.builder()
                    .status(200)
                    .message("Got group successfully")
                    .data(groupDto)
                    .build();
        } else {
            return ResponseDto.builder()
                    .status(404)
                    .message("Group not found")
                    .build();
        }
    }
}
