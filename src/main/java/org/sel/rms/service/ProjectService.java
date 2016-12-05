package org.sel.rms.service;
import org.sel.rms.entity.PaperEntity;
import org.sel.rms.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
* 生成于2016/10/29
*/
public interface ProjectService {

    public void publishProject(ProjectEntity projectEntity);


    public void modifyProject(ProjectEntity projectEntity);


    public void deleteProject(int idProject, int idTeacher);

    public ProjectEntity getProjectById(int id);

    public Page<ProjectEntity> getProjectEntitiesByIdOfTeacher(int id, Pageable pagea);


    Page<ProjectEntity> searchProjects(String keyWord, Pageable page);
}