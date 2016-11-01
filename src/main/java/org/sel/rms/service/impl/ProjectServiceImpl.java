package org.sel.rms.service.impl;
import org.sel.rms.entity.ProjectEntity;
import org.sel.rms.service.ProjectService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
* 生成于2016/10/29
*/
@Service
public class ProjectServiceImpl implements ProjectService {



    @Override
    public void publishProject(ProjectEntity projectEntity) {

    }

    @Override
    public void modifyProject(ProjectEntity projectEntity) {

    }

    @Override
    public void deleteProject(int idProject, int idTeacher) {

    }

    @Override
    public ProjectEntity getProjectById(int id) {
        return null;
    }

    @Override
    public Page<ProjectEntity> getProjectEntitiesByIdOfTeacher(int id, Pageable pagea) {
        return null;
    }


}