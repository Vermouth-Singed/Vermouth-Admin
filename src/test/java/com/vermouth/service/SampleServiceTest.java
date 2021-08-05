package com.vermouth.service;

import com.vermouth.model.SampleEntity;
import com.vermouth.service.repository.SampleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SampleServiceTest {
    @InjectMocks
    private SampleService sampleService;

    @Mock
    private SampleRepository sampleRepository;

    private Map<String, Object> result;

    private SampleEntity sampleEntity;

    @BeforeEach
    void setup(){
        this.result = new HashMap<>();
        this.result.put("msg","success");

        this.sampleEntity = SampleEntity.builder().
            title("title").
            description("description").
            build();
    }

    @Test
    @DisplayName("Sample 생성")
    void sample_생성() {
        when(sampleRepository.save(sampleEntity)).thenReturn(sampleEntity);

        Map<String, Object> map = this.sampleService.create(
            sampleEntity.getTitle(), sampleEntity.getDescription()
        );

        assertEquals(map.get("msg"), null);
    }

    @Test
    @DisplayName("Sample 리스트 읽기")
    void sample_리스트읽기() {
        when(sampleRepository.findAll(
            PageRequest.of(0, 1, Sort.by("id"))
        )).thenReturn(page);

        Map<String, Object> map = this.sampleService.readList(
            1, 1
        );

        assertEquals(map.get("msg"), null);
    }

    @Test
    @DisplayName("Sample 한개 읽기")
    void sample_한개읽기() {
        when(
            sampleRepository.findById(anyLong())).
                thenReturn(Optional.of(sampleEntity)
        );

        Map<String, Object> map = this.sampleService.readOne(anyLong());

        assertEquals(map.get("msg"), null);
    }

    @Test
    @DisplayName("Sample 수정")
    void sample_수정() {
        when(
            sampleRepository.findById(anyLong())).
                thenReturn(Optional.of(sampleEntity)
        );

        Map<String, Object> map =
            this.sampleService.update(
                anyLong(), sampleEntity.getTitle(), sampleEntity.getDescription()
            );

        assertEquals(map.get("msg"), null);
    }

    @Test
    @DisplayName("Sample 삭제")
    @Deprecated
    void delete() {
        when(
            sampleRepository.findById(anyLong())).
                thenReturn(Optional.of(sampleEntity)
        );

        Map<String, Object> map = this.sampleService.delete(anyLong());

        assertEquals(map.get("msg"), null);
    }

    private Page<SampleEntity> page = new Page<>() {
        @Override
        public int getTotalPages() {
            return 0;
        }

        @Override
        public long getTotalElements() {
            return 0;
        }

        @Override
        public <U> Page<U> map(
            Function<? super SampleEntity, ? extends U> converter
        ) {
            return null;
        }

        @Override
        public int getNumber() {
            return 0;
        }

        @Override
        public int getSize() {
            return 0;
        }

        @Override
        public int getNumberOfElements() {
            return 0;
        }

        @Override
        public List<SampleEntity> getContent() {
            return null;
        }

        @Override
        public boolean hasContent() {
            return false;
        }

        @Override
        public Sort getSort() {
            return null;
        }

        @Override
        public boolean isFirst() {
            return false;
        }

        @Override
        public boolean isLast() {
            return false;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Pageable nextPageable() {
            return null;
        }

        @Override
        public Pageable previousPageable() {
            return null;
        }

        @Override
        public Iterator<SampleEntity> iterator() {
            return null;
        }
    };
}