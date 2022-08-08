package com.kruger.inventario.service;

import com.kruger.inventario.Repository.UserRepository;
import com.kruger.inventario.models.Person;
import com.kruger.inventario.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public <S extends User> S save(S entity){
        return userRepository.save(entity);
    }
    public void deleteById(Long idUser) {
        userRepository.deleteById(idUser);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
