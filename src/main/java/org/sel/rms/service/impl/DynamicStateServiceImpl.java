package org.sel.rms.service.impl;

import org.sel.rms.entity.DynamicStateEntity;
import org.sel.rms.entity.PaperEntity;
import org.sel.rms.entity.ProjectEntity;
import org.sel.rms.repository.DynamicStateRepository;
import org.sel.rms.service.DynamicStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by xubowei on 15/11/2016.
 */
@Service
public class DynamicStateServiceImpl implements DynamicStateService{
    @Autowired
    DynamicStateRepository dynamicStateRepository;


    @Override
    public void addPaper(PaperEntity paperEntity) {
        LocalDateTime now = LocalDateTime.now();
        DynamicStateEntity dynamicStateEntity = new DynamicStateEntity(paperEntity.getIdTeacher(), paperEntity.getIdPaper(), "paper", paperEntity.getTitle(), paperEntity.getWriter(), Timestamp.valueOf(now));
        dynamicStateRepository.save(dynamicStateEntity);
    }


    @Override
    public void addProject(ProjectEntity projectEntity) {
        LocalDateTime now = LocalDateTime.now();
        DynamicStateEntity dynamicStateEntity = new DynamicStateEntity(projectEntity.getIdTeacher(), projectEntity.getIdProject(), "project", projectEntity.getName(), projectEntity.getMaster(),Timestamp.valueOf(now));
        dynamicStateRepository.save(dynamicStateEntity);
    }

    @Override
    public Page<DynamicStateEntity> getByPage(Pageable pageable) {
        return dynamicStateRepository.findAll(pageable);
    }
}
