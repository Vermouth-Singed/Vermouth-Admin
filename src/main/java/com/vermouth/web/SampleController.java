package com.vermouth.web;

import com.vermouth.model.ApiResult;
import com.vermouth.model.SampleDto;
import com.vermouth.service.SampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.vermouth.model.ApiResult.OK;

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
//        http post localhost:8080/api/sample title=title description=description
        log.info("Create");

        return OK(
            sampleService.create(
                sampleDto.getTitle(), sampleDto.getDescription()
            )
        );
    }

    @GetMapping("list/{pageNo}/{rowSize}")
    @ApiOperation(value = "리스트 읽기")
    public ApiResult readList(SampleDto sampleDto){
//        http get localhost:8080/api/sample/list/1/1
        log.info("Read List");

        return OK(
            sampleService.readList(
                sampleDto.getPageNo(), sampleDto.getRowSize()
            )
        );
    }

    @GetMapping("one/{id}")
    @ApiOperation(value = "한개 읽기")
    public ApiResult readOne(@PathVariable Long id){
//        http get localhost:8080/api/sample/one/1
        log.info("Read One");

        return OK(
            sampleService.readOne(id)
        );
    }

    @PatchMapping("{id}")
    @ApiOperation(value = "수정")
    public ApiResult update(@PathVariable Long id,
                            @RequestBody SampleDto sampleDto){
//        http patch localhost:8080/api/sample/1 title=제목수정
        log.info("Update");

        return OK(
            sampleService.update(
                id, sampleDto.getTitle(), sampleDto.getDescription()
            )
        );
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "삭제")
    public ApiResult delete(@PathVariable Long id){
//        http delete localhost:8080/api/sample/1
        log.info("Delete");

        return OK(
            sampleService.delete(id)
        );
    }
}