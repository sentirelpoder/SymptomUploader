package com.gerimedica.ontimecoding.rest;

import com.gerimedica.ontimecoding.model.Symptom;
import com.gerimedica.ontimecoding.service.SymptomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/symptom")
public class SymptomController {

    private final SymptomService symptomService;

    public SymptomController(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> upload(@RequestParam("file") MultipartFile file) {
        //upload file with service
        try {
            symptomService.upload(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Error occurred while uploading symptoms", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Symptoms are successfully uploaded",""));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Symptom>> getAllSymptoms(){
        List<Symptom> symptomList = new ArrayList<>();
        try {
           symptomList.addAll(symptomService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(symptomList);
        }

        return ResponseEntity.status(HttpStatus.OK).body(symptomList);

    }

    @GetMapping("/get/{code}")
    public ResponseEntity<Symptom> getSymptom(@PathVariable String code) {
        Symptom symptom;
        try {
            symptom = symptomService.getByCode(code);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(symptom);

    }

    @DeleteMapping("deleteAll")
    public ResponseEntity<ResponseMessage> deleteAll() {
        try {
            symptomService.deleteAll();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Error occurred while deleting symptoms",e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Symptoms are successfully deleted",""));
    }
}
