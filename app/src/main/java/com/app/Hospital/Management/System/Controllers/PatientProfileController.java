package com.app.Hospital.Management.System.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Hospital.Management.System.Services.PatientProfileService;
import com.app.Hospital.Management.System.entities.PatientProfile;
import com.app.Hospital.Management.System.repositories.PatientProfileRepository;

@RestController
@RequestMapping("/api/hospital/patients")
public class PatientProfileController {
	@Autowired
	private PatientProfileService patientProfileService;
	@Autowired
	private PatientProfileRepository patientrepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	 
	@PostMapping("/save")
	public ResponseEntity<PatientProfile> savePatient(@RequestBody PatientProfile p){
		//System.out.println("hi");
		//p.setPassword(passwordEncoder.encode(p.getPassword()));
		PatientProfile savedPatient=patientProfileService.savePatient(p);
		return ResponseEntity.ok(savedPatient);
	}
	
	
	@GetMapping("/get")
	public ResponseEntity<List<PatientProfile>> getAllPatients(){
		List<PatientProfile> patients=patientProfileService.getAllPatients();
		if(patients.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(patients);
	
	}
	
	@GetMapping("get/{id}")
    public ResponseEntity<PatientProfile> getPatientById(@PathVariable Long id) {
        Optional<PatientProfile> patient = patientProfileService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(patient.get());
        } else {
        	 return ResponseEntity.notFound().build();
        }
	}
	
	@PutMapping("put/{id}")
	public ResponseEntity<PatientProfile> updatePatient(@PathVariable Long id, @RequestBody PatientProfile p){
		Optional<PatientProfile> patient=patientProfileService.getPatientById(id);
		if(!patient.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		PatientProfile existingPatient=patient.get();
		existingPatient.setMedicalHistory(p.getMedicalHistory());
		existingPatient.setContactDetails(p.getContactDetails());
		PatientProfile updatedPatient=patientProfileService.updatePatient(existingPatient);
		return ResponseEntity.ok(updatedPatient);
		
	}
	@DeleteMapping("del/{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable Long id){
		if(!patientProfileService.getPatientById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		patientProfileService.deletePatient(id);
		return ResponseEntity.noContent().build();
	}
    
}
