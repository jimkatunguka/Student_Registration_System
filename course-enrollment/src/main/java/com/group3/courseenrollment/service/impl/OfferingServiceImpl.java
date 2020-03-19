package com.group3.courseenrollment.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.group3.courseenrollment.domain.Block;
import com.group3.courseenrollment.domain.Course;
import com.group3.courseenrollment.domain.Section;
import com.group3.courseenrollment.dto.OfferingDto;
import com.group3.courseenrollment.repository.BlockRepository;
import com.group3.courseenrollment.repository.CourseRepository;
import com.group3.courseenrollment.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.group3.courseenrollment.domain.Offering;
import com.group3.courseenrollment.exception.NoSuchResourceException;
import com.group3.courseenrollment.repository.OfferingRepository;
import com.group3.courseenrollment.service.OfferingService;

import javax.swing.text.html.Option;


@Service
@Secured({"ROLE_ADMIN"})
public class OfferingServiceImpl implements OfferingService{
	
	 	@Autowired
	    private OfferingRepository offeringRepository;

	 	@Autowired
		private CourseRepository courseRepository;

	 	@Autowired
		private BlockRepository blockRepository;

	 	@Autowired
		private SectionRepository sectionRepository;


	    @Transactional(propagation = Propagation.REQUIRES_NEW)
	    public List<Offering> getAllOfferings(){
	        return offeringRepository.findAll();
	    }


	    @Secured("ROLE_ADMIN")
	    @Transactional(propagation = Propagation.REQUIRES_NEW)
	    public Offering addOffering(OfferingDto offering){
			Optional<Course> course = courseRepository.findByCode(offering.getCourseId());
			course.orElseThrow(()->new NoSuchElementException("No Course Available"));


			Optional<Block> block = blockRepository.findByCode(offering.getBlockId());
			block.orElseThrow(()->new NoSuchElementException("No Such Offering Available"));

			List<Section> sections = offering.getSections().stream().map(sectionId->{
				return sectionRepository.findById(sectionId).get();
			}).collect(Collectors.toList());

			// Create Offering Object
			Offering offering1 = new Offering(course.get(),block.get());
			offering1.setSections(sections);

	    	return offeringRepository.save(offering1);
	    }


	    @Transactional(propagation = Propagation.REQUIRES_NEW)
	    public Offering getOffering(long offeringId) throws NoSuchResourceException {
			return offeringRepository.findById(offeringId)
					.orElseThrow(() -> new NoSuchResourceException("Can't find offering", offeringId));
	    }


	    @Transactional(propagation = Propagation.REQUIRES_NEW)
	    public Offering updateOffering(long offeringId, Offering new_Offering) throws NoSuchResourceException{
	        Offering offering = offeringRepository.findById(offeringId)
	                .orElseThrow(() -> new NoSuchResourceException("Can't find offering", offeringId));
	        offering.setCode(new_Offering.getCode());

			return offeringRepository.save(offering);
	    }



	    @Transactional(propagation = Propagation.REQUIRES_NEW)
	    public void deleteOffering(long offeringId) throws NoSuchResourceException{
	        Offering offering = offeringRepository.findById(offeringId)
	                .orElseThrow(() -> new NoSuchResourceException("Cant find offering", offeringId));
	        offeringRepository.delete(offering);
	    }

}
