package com.group3.courseenrollment.controller;

import com.group3.courseenrollment.domain.Offering;
import com.group3.courseenrollment.dto.OfferingDto;
import com.group3.courseenrollment.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/offerings")
public class OfferingController {

	@Autowired
	private OfferingService offeringService;

	@GetMapping
	public ResponseEntity<List<Offering>> getAllOfferings() {
		HttpHeaders headers = new HttpHeaders();
		List<Offering> offerings = offeringService.getAllOfferings();
		if (offerings == null) {
			return new ResponseEntity<List<Offering>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number Of Records Found", String.valueOf(offerings.size()));
		return new ResponseEntity<List<Offering>>(offerings, headers, HttpStatus.OK);
	}

	@GetMapping("/{offeringId}")
	public ResponseEntity<Offering> getOffering(@PathVariable long offeringId) {
		Offering offering = offeringService.getOffering(offeringId);
		if (offering == null) {
			return new ResponseEntity<Offering>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Offering>(offering, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Offering> addOffering(@RequestBody @Valid OfferingDto offeringDto) {

		Offering offering = offeringService.addOffering(offeringDto);

		return new ResponseEntity<Offering>(offering, HttpStatus.CREATED);

	}

}
