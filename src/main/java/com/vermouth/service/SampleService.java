package com.vermouth.service;

import com.vermouth.model.ErrorMSG;
import com.vermouth.model.SampleEntity;
import com.vermouth.service.repository.SampleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;

    public Map<String, Object> create(String title, String description) {
        Map<String, Object> result = new HashMap<>();

        try{
            SampleEntity sampleEntity = SampleEntity.builder().
                title(title).
                description(description).
                build();

            sampleRepository.save(sampleEntity);
        }catch (Exception e){
            result.put("msg", ErrorMSG.UNPREDICTABLE_ERROR.msg());

            log.error(ErrorMSG.UNPREDICTABLE_ERROR.msg(), e);
        }

        return result;
    }
}