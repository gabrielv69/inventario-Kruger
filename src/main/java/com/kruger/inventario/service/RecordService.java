package com.kruger.inventario.service;

import com.kruger.inventario.Repository.RecordRepository;
import com.kruger.inventario.models.Person;
import com.kruger.inventario.models.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    public List<Record> findAll() {
        return recordRepository.findAll();
    }
    public Optional<Record> findById(Long id) {
        return recordRepository.findById(id);
    }

    public void deleteById(Long idRecord) {
        recordRepository.deleteById(idRecord);
    }

    public <S extends Record> S save(S entity){
        return recordRepository.save(entity);
    }
}
