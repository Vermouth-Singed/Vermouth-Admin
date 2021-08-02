package com.vermouth.service;

import com.vermouth.model.ErrorMSG;
import com.vermouth.model.SampleEntity;
import com.vermouth.service.repository.SampleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public Map<String, Object> readList(int pageNo, int rowSize) {
        Map<String, Object> result = new HashMap<>();

        try{
            result.put("list", sampleRepository.findAll());
        }catch (Exception e){
            result.put("msg", ErrorMSG.UNPREDICTABLE_ERROR.msg());

            log.error(ErrorMSG.UNPREDICTABLE_ERROR.msg(), e);
        }

        return result;
    }

    public Map<String, Object> readOne(Long id) {
        Map<String, Object> result = new HashMap<>();

        try{
            Optional<SampleEntity> sampleEntity = sampleRepository.findById(id);

            if(sampleEntity.isPresent()){
                result.put("entity", sampleEntity.get());
            }else{
                result.put("msg", ErrorMSG.NOTEXIST_ERROR.msg());
            }
        }catch (Exception e){
            result.put("msg", ErrorMSG.UNPREDICTABLE_ERROR.msg());

            log.error(ErrorMSG.UNPREDICTABLE_ERROR.msg(), e);
        }

        return result;
    }
}