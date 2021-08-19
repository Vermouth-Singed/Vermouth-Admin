package com.vermouth.service;

import com.vermouth.model.ErrorMSG;
import com.vermouth.model.SampleEntity;
import com.vermouth.service.repository.SampleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;

    @Transactional
    public Map<String, Object> create(String title, String description) {
        Map<String, Object> result = new HashMap<>();

        try{
            SampleEntity sampleEntity = SampleEntity.builder().
                title(title).
                description(description).
                build();

            sampleRepository.save(sampleEntity);
        }catch (Exception e){
            log.error(
                ErrorMSG.UNPREDICTABLE_ERROR.msg() + ": {}", e.getMessage(), e
            );

            throw new RuntimeException();
        }

        return result;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> readList(int pageNo, int rowSize) {
        Map<String, Object> result = new HashMap<>();

        try{
            result.put("data", sampleRepository.findAll(
                PageRequest.of(pageNo-1, rowSize, Sort.by("id"))
            ).getContent());
        }catch (Exception e){
            log.error(
                ErrorMSG.UNPREDICTABLE_ERROR.msg() + ": {}", e.getMessage(), e
            );

            throw new RuntimeException();
        }

        return result;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> readOne(Long id) {
        Map<String, Object> result = new HashMap<>();

        try{
            sampleRepository.findById(id).map(entity -> {
                result.put("data", entity);

                return entity;
            }).orElseGet(() -> {
                result.put("msg", ErrorMSG.NOTEXIST_ERROR.msg());

                return null;
            });
        }catch (Exception e){
            log.error(
                ErrorMSG.UNPREDICTABLE_ERROR.msg() + ": {}", e.getMessage(), e
            );

            throw new RuntimeException();
        }

        return result;
    }

    @Transactional
    public Map<String, Object> update(Long id, String title, String description) {
        Map<String, Object> result = new HashMap<>();

        try{
            Optional<SampleEntity> maybeEntity = sampleRepository.findById(id);

            maybeEntity.map(entity -> {
                entity.setTitle(title != null ? title : entity.getTitle()).
                    setDescription(description != null ?
                        description : entity.getDescription());

                sampleRepository.save(entity);

                return entity;
            }).orElseGet(() -> {
                result.put("msg", ErrorMSG.NOTEXIST_ERROR.msg());

                return null;
            });
        }catch (Exception e){
            log.error(
                ErrorMSG.UNPREDICTABLE_ERROR.msg() + ": {}", e.getMessage(), e
            );

            throw new RuntimeException();
        }

        return result;
    }

    @Transactional
    public Map<String, Object> delete(Long id) {
        Map<String, Object> result = new HashMap<>();

        try{
            Optional<SampleEntity> maybeEntity = sampleRepository.findById(id);

            maybeEntity.map(entity -> {
                sampleRepository.delete(entity);

                return entity;
            }).orElseGet(() -> {
                result.put("msg", ErrorMSG.NOTEXIST_ERROR.msg());

                return null;
            });
        }catch (Exception e){
            log.error(
                ErrorMSG.UNPREDICTABLE_ERROR.msg() + ": {}", e.getMessage(), e
            );

            throw new RuntimeException();
        }

        return result;
    }
}