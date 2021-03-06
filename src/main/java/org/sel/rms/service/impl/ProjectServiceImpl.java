package org.sel.rms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.sel.rms.entity.ProjectEntity;
import org.sel.rms.exception.ProjectException;
import org.sel.rms.repository.ProjectRepository;
import org.sel.rms.service.DynamicStateService;
import org.sel.rms.service.ProjectService;
import org.sel.rms.status.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


/**
 * 生成于2016/10/29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    DynamicStateService dynamicStateService;

    @Value("config.teacher.key")
    String teacherKey;

    final static String KIND = "project";


    @Override
    public void publishProject(ProjectEntity projectEntity) {
        try {
            LocalDate localDate = LocalDate.now();
            projectEntity.setPublishTime(Date.valueOf(localDate));
            projectEntity = projectRepository.save(projectEntity);
            dynamicStateService.addProject(projectEntity);

        } catch (Exception ex) {
            throw new ProjectException("save paper error!", ex, ProjectStatus.DATABASE_ERROR);
        }
    }

    @Override
    public void modifyProject(ProjectEntity projectEntity) {
        ProjectEntity found = getProjectById(projectEntity.getIdProject());
        projectEntity.setPublishTime(found.getPublishTime());
        projectRepository.save(projectEntity);

    }

    @Override
    public void deleteProject(int idProject) {
        try {
            projectRepository.delete(idProject);
            dynamicStateService.deleteByKindAndIdContent(KIND, idProject);
        } catch (Exception ex) {
            throw new ProjectException("delete project error which id = " + idProject, ex, ProjectStatus.DATABASE_ERROR);
        }
    }

    @Override
    public ProjectEntity getProjectById(int id) {
        ProjectEntity projectEntity;
        projectEntity = projectRepository.findOne(id);
        if (projectEntity == null) {
            throw new ProjectException("no such project! idProject : " + id, ProjectStatus.NOT_FOUND);
        }
        return projectEntity;
    }

    @Override
    public Page<ProjectEntity> getProjectEntitiesByIdOfTeacher(int id, Pageable page) {
        Page<ProjectEntity> projectEntities;
        try {
            projectEntities = projectRepository.findByIdTeacher(id, page);
        } catch (Exception ex) {
            throw new ProjectException("get entities by teacher id error who id = " + id, ex, ProjectStatus.DATABASE_ERROR);
        }
        return projectEntities;

    }

    @Override
    public Page<ProjectEntity> searchProjects(String keyWord, Pageable page) {
        Page<ProjectEntity> projectEntities;
        String[] keyWords = keyWord.split(" ");
//        keyWord = StringUtils.join(keyWords, "|");
//        keyWord = "(" + keyWord + ")";
//        Arrays.fill(keyWords, keyWord);
        keyWord = StringUtils.join(keyWords, "%");
        keyWord = "%" + keyWord + "%";
        projectEntities = projectRepository.search(keyWord, page);
        return projectEntities;
    }

    @Override
    public List<ProjectEntity> getAllProjectsByIdTeacher(int id) {
        return projectRepository.findByIdTeacher(id);
    }


}