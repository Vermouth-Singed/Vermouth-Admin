package com.vermouth.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vermouth.model.TodoModel;
import com.vermouth.model.TodoRequest;
import com.vermouth.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private TodoService todoService;

    private TodoModel expected;

    @BeforeEach
    void setup(){
        this.expected = TodoModel.builder()
                .id(123L)
                .title("Test Title")
                .order(0L)
                .completed(false)
                .build();
    }

    @Test
    void create() throws Exception{
        when(this.todoService.add(any(TodoRequest.class)))
        .then(i->{
            TodoRequest todoRequest = i.getArgument(0, TodoRequest.class);
            return new TodoModel(
                this.expected.getId(),
                todoRequest.getTitle(),
                this.expected.getOrder(),
                this.expected.getCompleted()
            );
        });

        TodoRequest todoRequest = TodoRequest.builder()
                .title("Any Title").build();

        ObjectMapper mapper = new ObjectMapper();

        String content = mapper.writeValueAsString(todoRequest);

        this.mvc.perform(post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title")
            .value("Any Title"));
    }

    @Test
    void readOne() {
    }
}