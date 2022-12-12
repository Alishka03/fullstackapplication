package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Transactional
    public void saveUser(User user){
        userRepository.save(user);
    }
    @Transactional
    public void editUser(User user,Long id){
        user.setId(id);
        userRepository.save(user);
    }
    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
}
