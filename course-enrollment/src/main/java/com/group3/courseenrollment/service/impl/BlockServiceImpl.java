package com.group3.courseenrollment.service.impl;

import com.group3.courseenrollment.domain.Block;
import com.group3.courseenrollment.exception.NoSuchResourceException;
import com.group3.courseenrollment.repository.BlockRepository;
import com.group3.courseenrollment.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BlockServiceImpl implements BlockService {

	@Autowired
    private BlockRepository blockRepository;

    @Secured({"ROLE_STUDENT","ROLE_ADMIN"})
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Block> getAllBlocks(){
        return blockRepository.findAll();
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Block addBlock(Block block){
        return blockRepository.save(block);
    }


    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Block getBlock(String blockId) throws NoSuchResourceException{
        return blockRepository.findByCode(blockId)
                .orElseThrow(() -> new NoSuchResourceException("Can't find Block", 1L));
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Block updateBlock(String blockId, Block new_Block) throws NoSuchResourceException{
        Block block = blockRepository.findByCode(blockId)
                .orElseThrow(() -> new NoSuchResourceException("Can't find block", 1L));
        block.setBlockSeqNum(new_Block.getBlockSeqNum());
        block.setCode(new_Block.getCode());
        block.setEndDate(new_Block.getEndDate());
        block.setName(new_Block.getName());
        block.setSemester(new_Block.getSemester());
        block.setStartDate(new_Block.getStartDate());

        return blockRepository.save(block);
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Void> deleteBlock(String blockId) throws NoSuchResourceException{
        Block block = blockRepository.findByCode(blockId)
                .orElseThrow(() -> new NoSuchResourceException("Cant find block", 1L));
        blockRepository.delete(block);
        return  ResponseEntity.noContent().build();
    }


}
