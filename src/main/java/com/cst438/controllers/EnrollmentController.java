package com.cst438.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentDTO;
import com.cst438.domain.EnrollmentRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"})
public class EnrollmentController {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EnrollmentRepository enrollmentRepository;

	/*
	 * endpoint used by registration service to add an enrollment to an existing
	 * course.
	 */
	@PostMapping("/enrollment")
	@Transactional
	public EnrollmentDTO addEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
		
		//TODO  complete this method in homework 4
		Enrollment enrollment = new Enrollment();
		enrollment.setStudentEmail(enrollmentDTO.studentEmail);
		enrollment.setStudentName(enrollmentDTO.studentName);
		Course course = new Course();
		course.setCourse_id(enrollmentDTO.course_id);
		enrollment.setCourse(course);
		
		Enrollment saved = enrollmentRepository.save(enrollment);
		
		enrollmentDTO.id = saved.getId();
		enrollmentDTO.studentName = saved.getStudentName();
		enrollmentDTO.studentEmail = saved.getStudentEmail();
		enrollmentDTO.course_id = saved.getCourse().getCourse_id();
		
		return enrollmentDTO;
		
	}

}
