package com.vermouth.service;

import com.vermouth.model.TodoModel;
import com.vermouth.model.TodoRequest;
import com.vermouth.service.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void add() {
        when(this.todoRepository.save((any(TodoModel.class))))
                .then(AdditionalAnswers.returnsFirstArg());

        TodoRequest expected = TodoRequest.builder()
                .title("Test Title").build();

        TodoModel actual = this.todoService.add(expected);

        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    void searchById() {
        TodoModel todoModel = TodoModel.builder()
                .id(123L)
                .title("Title")
                .order(0L)
                .completed(false)
                .build();

        Optional<TodoModel> optional = Optional.of(todoModel);

        given(this.todoRepository.findById(anyLong()))
                .willReturn(optional);

        TodoModel actual = this.todoService.searchById(123L);
        TodoModel expected = optional.get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getOrder(), actual.getOrder());
        assertEquals(expected.getCompleted(), actual.getCompleted());
    }

    @Test
    public void searchByIdFailed(){
        given(this.todoRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, ()->{
            this.todoService.searchById(123L);
        });
    }
}