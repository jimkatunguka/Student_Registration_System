package com.group3.courseenrollment.controller;


import com.group3.courseenrollment.domain.Section;
import com.group3.courseenrollment.dto.SectionDto;
import com.group3.courseenrollment.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping
    public @ResponseBody List<Section> getSections(){
        return sectionService.getAllSections();
    }


    @PostMapping
    public ResponseEntity<Section> addSections(@RequestBody SectionDto sectionDto){
        Section section = sectionService.addSection(sectionDto);
        return new ResponseEntity<Section>(section, HttpStatus.CREATED);
    }

}
