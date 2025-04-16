package br.com.cmdev.data.jpa.service;


import br.com.cmdev.data.jpa.dto.UserRequest;
import br.com.cmdev.data.jpa.dto.UserResponse;
import br.com.cmdev.data.jpa.entity.Address;
import br.com.cmdev.data.jpa.entity.User;
import br.com.cmdev.data.jpa.mapper.UserMapper;
import br.com.cmdev.data.jpa.repository.UserRepository;
import br.com.cmdev.data.jpa.utils.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse saveUser(UserRequest request) {
        Address address = mapper.addressRequestToAddress(request.address());
        User user = mapper.userRequestToUser(request);

        user.setPassword(passwordEncoder.encode(request.password()));
        address.setUser(user);
        user.setAddress(address);
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

    @Transactional
    public void updateUser(Long userId, UserRequest request) {
        var user = repository.findById(userId);
        if(user.isPresent()) {
            user.get().setUserId(userId);
            user.get().setName(request.name());
            user.get().setEmail(request.email());
            user.get().setPassword(passwordEncoder.encode(request.password()));
            user.get().setRole(UserRole.valueOf(request.role()));
            user.get().setActive(request.isActive());
            repository.save(user.get());
        }
    }

    @Transactional
    public void deleteUser(Long id) {
        Optional<User> user = repository.findById(id);
        if(user.isPresent()) {
            repository.delete(user.get());
        }
    }

}
