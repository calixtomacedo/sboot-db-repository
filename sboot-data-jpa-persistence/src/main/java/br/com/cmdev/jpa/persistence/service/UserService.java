package br.com.cmdev.jpa.persistence.service;


import br.com.cmdev.jpa.persistence.dto.UserRequest;
import br.com.cmdev.jpa.persistence.dto.UserResponse;
import br.com.cmdev.jpa.persistence.entity.User;
import br.com.cmdev.jpa.persistence.mapper.UserMapper;
import br.com.cmdev.jpa.persistence.repository.UserRepository;
import br.com.cmdev.jpa.persistence.utils.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserRepository repository;

    public UserResponse saveUser(UserRequest request) {
        User user = mapper.userRequestToUser(request);
        User newUser = repository.save(user);
        UserResponse response = mapper.userToUserResponse(newUser);
        return response;
    }

    public List<UserResponse> getAllUsers() {
        Iterable<User> users = repository.findAll();
        List<UserResponse> response = mapper.userListToUserResponseList(users);
        return response;
    }

    public UserResponse getUserById(Long id) {
        //var user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found some user with id ".concat(String.valueOf(id))));
        var user = repository.findById(id).orElse(null);
        var response = mapper.userToUserResponse(user);
        return response;
    }

    public void updateUser(Long id, UserRequest request) {
        var user = repository.findById(id);
        if(user.isPresent()) {
            user.get().setId(id);
            user.get().setName(request.name());
            user.get().setEmail(request.email());
            user.get().setPassword(request.password());
            user.get().setRole(UserRole.valueOf(request.role()));
            user.get().setActive(request.isActive());
            user.get().setChangeDate(LocalDateTime.now());
            repository.save(user.get());
        }
    }

    public void deleteUser(Long id) {
        Optional<User> user = repository.findById(id);
        if(user.isPresent()) {
            repository.delete(user.get());
        }
    }

}
