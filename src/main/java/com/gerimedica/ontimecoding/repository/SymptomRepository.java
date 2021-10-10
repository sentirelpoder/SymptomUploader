package com.gerimedica.ontimecoding.repository;

import com.gerimedica.ontimecoding.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, String> {

    Symptom findByCode(String code);
}
