package com.vermouth.web;

import com.vermouth.model.TodoRequest;
import com.vermouth.model.TodoResponse;
import com.vermouth.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/todo")
@Api(tags = "Todo 컨트롤러")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    @ApiOperation(value = "생성")
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest todoRequest){
        log.info("CREATE");

        if(ObjectUtils.isEmpty(todoRequest.getTitle())){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
            new TodoResponse(this.todoService.add(todoRequest))
        );
    }

    @GetMapping("{id}")
    @ApiOperation(value = "한개만 읽기")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id){
        log.info("READ ONE");

        return ResponseEntity.ok(
            new TodoResponse(this.todoService.searchById(id))
        );
    }

    @GetMapping
    @ApiOperation(value = "전체 읽기")
    public ResponseEntity<List<TodoResponse>> readAll(){
        log.info("READ ALL");

        return ResponseEntity.ok(this.todoService.searchAll().stream()
                                .map(TodoResponse::new)
                                .collect(Collectors.toList())
        );
    }

    @PatchMapping("{id}")
    @ApiOperation(value = "수정")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest todoRequest){
        log.info("UPDATE");

        return ResponseEntity.ok(
            new TodoResponse(this.todoService.updateById(id, todoRequest))
        );
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "한개만 삭제")
    public ResponseEntity<?> deleteOne(@PathVariable Long id){
        log.info("DELETE");

        this.todoService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @ApiOperation(value = "전체 삭제")
    public ResponseEntity<?> deleteAll(){
        log.info("DELETE ALL");

        this.todoService.deleteAll();

        return ResponseEntity.ok().build();
    }
}