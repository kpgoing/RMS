package org.sel.rms.service;
import org.sel.rms.entity.PaperEntity;
import org.sel.rms.status.PaperStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
* 生成于2016/10/29
*/
public interface PaperService {

    void publishPaper(PaperEntity paperEntity);

    void modifyPaper(PaperEntity paperEntity, HttpServletRequest request);

    void deletePaper(int idPaper, int idTeacher, HttpServletRequest request);

    PaperEntity getPaperById(int id);

    Page<PaperEntity> getPageEntitiesByIdOfTeacher(int id, Pageable pagea);

    String uploadFile(HttpServletRequest request, MultipartFile file);

    PaperStatus deleteFile(HttpServletRequest request, String oldPath);
}