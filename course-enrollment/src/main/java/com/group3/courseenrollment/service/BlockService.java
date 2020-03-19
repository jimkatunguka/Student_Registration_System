package com.group3.courseenrollment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.group3.courseenrollment.domain.Block;
import com.group3.courseenrollment.exception.NoSuchResourceException;

public interface BlockService {
	
	public List<Block> getAllBlocks();
	
	public Block addBlock(Block block);
	
	public Block getBlock(String courseId) throws NoSuchResourceException;
	
	public Block updateBlock(String blockId, Block new_Block) throws NoSuchResourceException;
	
	public ResponseEntity<Void> deleteBlock(String blockId) throws NoSuchResourceException;

}
