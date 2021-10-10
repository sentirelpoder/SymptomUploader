package com.gerimedica.ontimecoding.service;

import com.gerimedica.ontimecoding.model.Symptom;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SymptomService {

    void upload(MultipartFile file);

    List<Symptom> getAll();

    Symptom getByCode(String code);

    void deleteAll();
}
