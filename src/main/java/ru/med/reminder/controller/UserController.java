package ru.med.reminder.controller;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.med.reminder.dto.UserDto;
import ru.med.reminder.service.UserService;

@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@RestController
public class UserController {

    private final UserService service;

    @PostMapping("register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto user) {
        return new ResponseEntity<>(service.register(user), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserDto> getByLogin(@PathVariable String login) {
        return new ResponseEntity<>(service.findByLogin(login), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteById(@PathVariable UUID id) {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }
}
