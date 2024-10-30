package com.minhdubai.Giftback.controller;

import com.minhdubai.Giftback.domain.dto.GroupDto;
import com.minhdubai.Giftback.service.GroupService;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllGroups() {
        ResponseDto response = groupService.getAllGroups();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createGroup(@RequestBody GroupDto groupDto) {
        ResponseDto response = groupService.createGroup(groupDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto) {
        ResponseDto response = groupService.updateGroup(id, groupDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteGroup(@PathVariable Integer id) {
        ResponseDto response = groupService.deleteGroup(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
