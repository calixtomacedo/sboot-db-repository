package br.com.cmdev.jdbc.api.controller;

import br.com.cmdev.jdbc.api.dto.UserRequest;
import br.com.cmdev.jdbc.api.dto.UserResponse;
import br.com.cmdev.jdbc.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody @Valid UserRequest request) {
        UserResponse response = service.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        //Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        //List<UserResponse> allUsers = service.getAllUsers(pageable);
        List<UserResponse> allUsers = service.getAllUsers();
        if(Objects.nonNull(allUsers) && !allUsers.isEmpty()) {
            return ResponseEntity.ok(allUsers);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        UserResponse response = service.getUserById(id);
        if(Objects.nonNull(response)) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserRequest request, @PathVariable("id") Long id) {
        service.updateUser(id, request);
        return this.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserResponse>> deteleUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        //Pageable pageable = PageRequest.of(0, 20, Sort.by("name"));
        //List<UserResponse> allUsers = service.getAllUsers(pageable);
        List<UserResponse> allUsers = service.getAllUsers();
        return allUsers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allUsers);
    }
}
