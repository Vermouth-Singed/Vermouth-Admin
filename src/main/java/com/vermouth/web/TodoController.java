package com.vermouth.web;

import com.vermouth.model.TodoRequest;
import com.vermouth.model.TodoResponse;
import com.vermouth.service.TodoService;
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
public class TodoController {
    private final TodoService todoService;

    @PostMapping
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
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id){
        log.info("READ ONE");

        return ResponseEntity.ok(
            new TodoResponse(this.todoService.searchById(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll(){
        log.info("READ ALL");

        return ResponseEntity.ok(this.todoService.searchAll().stream()
                                .map(TodoResponse::new)
                                .collect(Collectors.toList())
        );
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest todoRequest){
        log.info("UPDATE");

        return ResponseEntity.ok(
            new TodoResponse(this.todoService.updateById(id, todoRequest))
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id){
        log.info("DELETE");

        this.todoService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        log.info("DELETE ALL");

        this.todoService.deleteAll();

        return ResponseEntity.ok().build();
    }
}