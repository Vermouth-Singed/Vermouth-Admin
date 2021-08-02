package com.vermouth.web;

import com.vermouth.model.ApiResult;
import com.vermouth.model.SampleDto;
import com.vermouth.service.SampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/sample")
@Api(tags = "Sample 컨트롤러")
public class SampleController {
    private final SampleService sampleService;

    @PostMapping()
    @ApiOperation(value = "생성")
    public ApiResult create(@RequestBody SampleDto sampleDto){
        log.info("Create");

        return new ApiResult(
            sampleService.create(sampleDto.getTitle(), sampleDto.getDescription())
        );
    }

    @GetMapping("list/{pageNo}/{rowSize}")
    @ApiOperation(value = "리스트 읽기")
    public ApiResult readList(SampleDto sampleDto){
        log.info("Read List");

        return new ApiResult(
            sampleService.readList(sampleDto.getPageNo(), sampleDto.getRowSize())
        );
    }

    @GetMapping("one/{id}")
    @ApiOperation(value = "리스트 읽기")
    public ApiResult readOne(@PathVariable Long id){
        log.info("Read One");

        return new ApiResult(
            sampleService.readOne(id)
        );
    }
}