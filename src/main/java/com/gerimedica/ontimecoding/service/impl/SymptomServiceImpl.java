package com.gerimedica.ontimecoding.service.impl;

import com.gerimedica.ontimecoding.exception.APIException;
import com.gerimedica.ontimecoding.model.Symptom;
import com.gerimedica.ontimecoding.repository.SymptomRepository;
import com.gerimedica.ontimecoding.service.SymptomService;
import com.gerimedica.ontimecoding.util.SymptomBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SymptomServiceImpl implements SymptomService {

    SymptomRepository symptomRepository;

    public SymptomServiceImpl(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    @Override
    public void upload(MultipartFile file) {
        List<Symptom> symptomList = SymptomBuilder.readSymptomsFromFile(file);
        symptomRepository.saveAll(symptomList);
    }

    @Override
    public List<Symptom> getAll() {
        return this.symptomRepository.findAll(Sort.by(Sort.Direction.ASC,"sortingPriority"));
    }

    @Override
    public Symptom getByCode(String code) {
        Symptom symptom = this.symptomRepository.findByCode(code);
        if(symptom == null)
            throw new APIException(String.format("Symptom with code %s not found", code));
        return symptom;
    }

    @Override
    public void deleteAll() {
        this.symptomRepository.deleteAll();
    }
}
