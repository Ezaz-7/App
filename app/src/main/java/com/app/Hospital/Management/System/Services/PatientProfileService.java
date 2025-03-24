package com.app.Hospital.Management.System.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.Hospital.Management.System.entities.PatientProfile;
import com.app.Hospital.Management.System.repositories.AppointmentRepository;
import com.app.Hospital.Management.System.repositories.PatientProfileRepository;

import jakarta.transaction.Transactional;

@Service
public class PatientProfileService {
	@Autowired
	private PatientProfileRepository patientRepository;
	@Autowired 
	private AppointmentRepository appointmentRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public PatientProfile updatePatient(PatientProfile p) {
		//p.setPassword(patientRepository.findById(p.getPatientId()).get().getPassword());
		//System.out.println(p.getPassword());
		return patientRepository.save(p);
	}
	public PatientProfile savePatient(PatientProfile p) {
		//p.setPassword(passwordEncoder.encode(p.getPassword()));
		return patientRepository.save(p);
	}
//	
//	@Transactional
//	public PatientProfile loginUser(LoginDto loginDto) {
//		PatientProfile p=patientRepository.findByEmail(loginDto.getEmail());
//		if(p!=null && p.getPassword().equals(loginDto.getPassword())) {
//			return p;
//		}
//		else {
//			throw new RuntimeException("Invalid email or password");
//		}
//	}

	public List<PatientProfile> getAllPatients() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

	public Optional<PatientProfile> getPatientById(Long id) {
		// TODO Auto-generated method stub
		return patientRepository.findById(id);
	}
	@Transactional
	public void deletePatient(Long id) {
		appointmentRepository.deleteByPatientId(id);
		 patientRepository.deleteById(id);
	}
	
	
	
	
}
