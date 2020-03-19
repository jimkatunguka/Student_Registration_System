package com.group3.courseenrollment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.group3.courseenrollment.domain.Course;
import com.group3.courseenrollment.exception.NoSuchResourceException;
import com.group3.courseenrollment.repository.CourseRepository;
import com.group3.courseenrollment.service.CourseService;


@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
    private CourseRepository courseRepository;

    @Secured({"ROLE_STUDENT","ROLE_ADMIN"})
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Course addCourse(Course course){
        return courseRepository.save(course);
    }


    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Course getCourse(String courseId) throws NoSuchResourceException{
        return courseRepository.findByCode(courseId)
                .orElseThrow(() -> new NoSuchResourceException("Can't find Course", 1L));
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Course updateCourse(String courseId, Course new_Course) throws NoSuchResourceException{
        Course course = courseRepository.findByCode(courseId)
                .orElseThrow(() -> new NoSuchResourceException("Can't find course", 1L));
        course.setCode(new_Course.getCode());
        course.setName(new_Course.getName());
        course.setDescription(new_Course.getDescription());

        return courseRepository.save(course);
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Void> deleteCourse(String courseId) throws NoSuchResourceException{
        Course course = courseRepository.findByCode(courseId)
                .orElseThrow(() -> new NoSuchResourceException("Cant find course", 1L));
        courseRepository.delete(course);
        return  ResponseEntity.noContent().build();
    }

}
