package com.app.Hospital.Management.System.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.app.Hospital.Management.System.Services.DoctorScheduleService;
import com.app.Hospital.Management.System.entities.DoctorSchedule;
import com.app.Hospital.Management.System.entities.PatientProfile;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/hospital/doctors")
public class DoctorScheduleController {
	
	@Autowired
	private DoctorScheduleService doctorScheduleService;
	
	@PostMapping("/save")
	@Transactional
	public ResponseEntity<DoctorSchedule> saveDoctor(@RequestBody DoctorSchedule d){
		DoctorSchedule doc= doctorScheduleService.saveDoctor(d);
		return ResponseEntity.ok(doc);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<DoctorSchedule>> getAllDoctors(){
		List<DoctorSchedule> doctors=doctorScheduleService.getAllDoctors();
		if(doctors.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(doctors);
	}
	
	@PutMapping("/create/{id}")
	public ResponseEntity<String> createAvailability(@PathVariable Long id){
		String s=doctorScheduleService.createAvailability(id);
		return ResponseEntity.ok(s);
		
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<DoctorSchedule> getDoctorById(@PathVariable Long id){
//		Optional<DoctorSchedule> optional=doctorScheduleService.getById(id);
//		if(optional!=null) {
//			return ResponseEntity.ok(optional.get());
//		}
//		else {
//			return ResponseEntity.notFound().build();
//		}
//		
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
//		if(!doctorScheduleService.getById(id).isPresent()) {
//			return ResponseEntity.notFound().build();
//		}
//		 doctorScheduleService.deleteDoctor(id);
//	        return ResponseEntity.noContent().build();
//	}
	
}
