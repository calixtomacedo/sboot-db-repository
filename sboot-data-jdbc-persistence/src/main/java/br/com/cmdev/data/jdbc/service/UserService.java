package br.com.cmdev.data.jdbc.service;

import br.com.cmdev.data.jdbc.dto.UserRequest;
import br.com.cmdev.data.jdbc.dto.UserResponse;
import br.com.cmdev.data.jdbc.entity.User;
import br.com.cmdev.data.jdbc.mapper.UserMapper;
import br.com.cmdev.data.jdbc.repository.UserRepository;
import br.com.cmdev.data.jdbc.utils.UserRole;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserMapper mapper;

    private final UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserMapper mapper, UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse saveUser(UserRequest request) {
        User user = mapper.userRequestToUser(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        User newUser = repository.save(user);
        UserResponse response = mapper.userToUserResponse(newUser);
        return response;
    }

    public List<UserResponse> getAllUsers(Pageable pageable) {
        Iterable<User> users = repository.findAll(pageable);
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
            user.get().setPassword(passwordEncoder.encode(request.password()));
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
