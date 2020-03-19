package com.group3.courseenrollment.controller;


import com.group3.courseenrollment.domain.Block;
import com.group3.courseenrollment.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/blocks")
public class BlockController {

    @Autowired
    private BlockService blockService;

    @PostMapping
    public ResponseEntity<Block> addBlock(@RequestBody @Valid Block block){
        Block savedBlock = blockService.addBlock(block);
        return ResponseEntity.created(URI.create("/blocks/" + savedBlock.getCode())).build();
    }

    @GetMapping("/{blockId}")
    public ResponseEntity<List<Block>> loadBlocks(@PathVariable String blockId){
        return ResponseEntity.ok(blockService.getAllBlocks());
    }

}
