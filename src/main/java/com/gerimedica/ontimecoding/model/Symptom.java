package com.gerimedica.ontimecoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "symptoms")
public class Symptom {

    @Id
    @Column(unique=true)
    private String id;

    private String source;

    private String codeListCode;

    @Column(unique=true)
    private String code;

    private String displayValue;

    private String longDescription;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Integer sortingPriority;

}
