package com.kruger.inventario.service;

import com.kruger.inventario.Repository.PersonRepository;
import com.kruger.inventario.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired  //Autoinjection
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public <S extends Person> S save(S entity){
        return personRepository.save(entity);
    }

    public void deleteById(Long idPersona) {
        personRepository.deleteById(idPersona);
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

}
