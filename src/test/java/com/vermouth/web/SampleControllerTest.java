package com.vermouth.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vermouth.model.SampleDto;
import com.vermouth.service.SampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SampleController.class)
@DisplayName("Sample 컨트롤러 테스트")
class SampleControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    SampleService sampleService;

    @Autowired
    private ObjectMapper objectMapper;

    private Map<String, Object> result;

    private SampleDto sampleDto;

    @BeforeEach
    void setup(){
        this.result = new HashMap<>();
        this.result.put("msg","success");

        this.sampleDto = SampleDto.builder().
            title("title").
            description("description").
            build();
    }

    @Test
    @DisplayName("Sample 생성")
    void sample_생성() throws Exception{
        given(sampleService.create(
            "title", "description"
        )).willReturn(result);

        mvc.perform(
            post("/api/sample").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(sampleDto))
        ).
        andDo(print()).
        andExpect(status().isOk()).
        andExpect(
            jsonPath("$.msg").value("success")
        );
    }

    @Test
    @DisplayName("Sample 한개 읽기")
    void sample_한개읽기() throws Exception{
        given(sampleService.readOne(1L)).willReturn(result);

        mvc.perform(get("/api/sample/one/1")).
        andDo(print()).
        andExpect(status().isOk()).
        andExpect(
            jsonPath("$.msg").value("success")
        );
    }

//    @Test
//    @DisplayName("Sample 리스트 읽기")
//    void readList() {
//    }
//
//    @Test
//    @DisplayName("Sample 수정")
//    void update() {
//    }
//
//    @Test
//    @DisplayName("Sample 삭제")
//    void delete() {
//    }
}