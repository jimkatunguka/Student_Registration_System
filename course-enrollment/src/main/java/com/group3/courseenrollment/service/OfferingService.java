package com.group3.courseenrollment.service;

import com.group3.courseenrollment.domain.Offering;
import com.group3.courseenrollment.dto.OfferingDto;
import com.group3.courseenrollment.exception.NoSuchResourceException;

import java.util.List;

public interface OfferingService {
	public List<Offering> getAllOfferings();
	public Offering addOffering(OfferingDto offeringDto);
	public Offering getOffering(long offeringId) throws NoSuchResourceException;
	public Offering updateOffering(long offeringId, Offering new_Offering) throws NoSuchResourceException;
	public void deleteOffering(long offeringId) throws NoSuchResourceException;
}