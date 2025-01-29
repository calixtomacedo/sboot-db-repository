package br.com.cmdev.jdbc.api.service;

import br.com.cmdev.jdbc.api.dto.UserRequest;
import br.com.cmdev.jdbc.api.dto.UserResponse;
import br.com.cmdev.jdbc.api.entity.User;
import br.com.cmdev.jdbc.api.mapper.UserMapper;
import br.com.cmdev.jdbc.api.repository.UserRepository;
import br.com.cmdev.jdbc.api.utils.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        repository.save(user);
        return repository.findByNameAndEmail(user).orElse(null);
    }

    public List<UserResponse> getAllUsers() {
        List<UserResponse> usersResponse = repository.findAll();
        return usersResponse;
    }

    public UserResponse getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UserResponse updateUser(Long id, UserRequest request) {
        var user = repository.findById(id);
        if(user.isPresent()) {
            User updatedUser = User.builder().id(id)
                    .name(request.name())
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .role(UserRole.valueOf(request.role()))
                    .active(request.isActive())
                    .changeDate(LocalDateTime.now()).build();
            repository.update(updatedUser);
            return mapper.userToUserResponse(updatedUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
       repository.delete(id);
    }

}
