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
        log.info("CREATE");

        return new ApiResult(
            sampleService.create(sampleDto.getTitle(), sampleDto.getDescription())
        );
    }
}