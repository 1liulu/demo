package com.example.demo1.mapper;


import com.example.demo1.bean.Patient;

import java.util.List;

public interface PatientMapper {
    public List<Patient> findbyuid(Patient patient);
    public int addpatient(Patient patient);
}
