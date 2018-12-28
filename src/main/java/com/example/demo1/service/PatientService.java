package com.example.demo1.service;


import com.example.demo1.bean.Patient;
import com.example.demo1.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientMapper patientMapper;
    public List<Patient> findbyuid(Patient patient){
        return patientMapper.findbyuid(patient);
    }
    public int addpatient(Patient patient){
        return patientMapper.addpatient(patient);
    }
}
