package com.group3.courseenrollment.service.impl;

import com.group3.courseenrollment.domain.Block;
import com.group3.courseenrollment.service.BlockService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@SpringJUnitWebConfig
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockServiceImplTest {

    @MockBean
    private BlockServiceImpl blockService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllBlocks() {
        given(blockService.getAllBlocks()).willCallRealMethod();
    }

    @Test
    public void addBlock() {
        given(blockService.getAllBlocks()).willCallRealMethod();
    }

    @Test
    public void getBlock() {
        given(blockService.getAllBlocks()).willCallRealMethod();
    }

    @Test
    public void updateBlock() {
        given(blockService.updateBlock("12", new Block())).willCallRealMethod();
    }

    @Test
    public void deleteBlock() {
        given(blockService.deleteBlock("12")).willCallRealMethod();
    }
}