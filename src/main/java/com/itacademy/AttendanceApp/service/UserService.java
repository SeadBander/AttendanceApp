package com.itacademy.AttendanceApp.service;

import com.itacademy.AttendanceApp.entity.User;
import com.itacademy.AttendanceApp.exception.UserNotFoundException;
import com.itacademy.AttendanceApp.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Getter
@Setter
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
//                .orElseThrow(() -> new UserNotFoundException(String.format("User [%s] not found", username)));
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User [%s] not found", username)));
    }

    public User updateUser(User user) {
        User updateResponse = userRepository.save(user);
        return updateResponse;
    }

    public User findById(Long id) throws IOException {
        return userRepository.findById(id).orElseThrow(() -> new IOException(String.format("User of id=[%s] not found", id)));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
