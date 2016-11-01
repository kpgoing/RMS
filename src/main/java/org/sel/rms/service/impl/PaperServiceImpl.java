package org.sel.rms.service.impl;
import ch.qos.logback.core.status.Status;
import org.sel.rms.entity.PaperEntity;
import org.sel.rms.exception.PaperException;
import org.sel.rms.repository.PaperRepository;
import org.sel.rms.service.PaperService;
import org.sel.rms.status.PaperStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


/**
* 生成于2016/10/29
*/
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    PaperRepository paperRepository;


    @Value("config.teacher.key")
    String teacherKey;

    @Override
    public void publishPaper(PaperEntity paperEntity) {
        try {
            paperRepository.save(paperEntity);
        } catch (Exception ex) {
            throw new PaperException("save paper error!", ex, PaperStatus.DATABASE_ERROR);
        }
    }

    public PaperEntity getPaperById(int id) {
        PaperEntity paperEntity;
        paperEntity = paperRepository.findOne(id);
        if (paperEntity == null) {
            throw new PaperException("no such paper! idPaper : " + id, PaperStatus.NOT_FOUND);
        }
        return paperEntity;
    }

    @Override
    public Page<PaperEntity> getPageEntitiesByIdOfTeacher(int id, Pageable page) {
        Page<PaperEntity> paperEntities;
        try {
            paperEntities = paperRepository.findByIdTeacher(id, page);
        } catch (Exception ex) {
            throw new PaperException("get entities by teacher id error who id = " + id, ex, PaperStatus.DATABASE_ERROR);
        }
        return paperEntities;
    }

    @Override
    public void modifyPaper(PaperEntity paperEntity, HttpServletRequest request) {
        PaperEntity found = getPaperById(paperEntity.getIdPaper());
        deleteFile(request, found.getContent());
        paperRepository.save(paperEntity);
    }

    @Override
    public void deletePaper(int idPaper, int idTeacher) {
        PaperEntity paperEntity = paperRepository.findOne(idPaper);
        if (paperEntity.getIdTeacher() == idTeacher) {
            try {
                paperRepository.delete(idPaper);
            } catch (Exception ex) {
                throw new PaperException("delete paper error which id = " + id, ex, PaperStatus.NOT_FOUND);
            }
        } else {
            throw new PaperException("delete paper error because the paper is not belong to the teacher which idPaper =  " + idPaper + " idTeahcer = " + idTeacher, PaperStatus.PERMISSIOM_DENY);
        }
    }

    @Override
    public String uploadFile(HttpServletRequest request, MultipartFile file) {
        int id = Integer.parseInt((String) request.getSession().getAttribute(teacherKey));
        String sqPath;
        String fileName = file.getOriginalFilename();
        String extensionName = fileName.substring(fileName.indexOf(".") + 1);
        fileName = fileName + new Date().getTime() + extensionName;
        String path = request.getServletContext().getRealPath(File.separator + "upload") + File.separator + id + File.separator + fileName;

        File fil = new File(path);
        if (!fil.exists())
            fil.mkdirs();
        try {
            file.transferTo(fil);
            sqPath = path.substring(path.indexOf(File.separator + "upload"));
        } catch (IOException e) {
            throw new PaperException("upload file error!", e, PaperStatus.UPLOAD_PAPER_ERROR);
        }
        return sqPath;
    }


    @Override
    public PaperStatus deleteFile(HttpServletRequest request, String oldPath) {
        oldPath = request.getServletContext().getRealPath("") + oldPath;
        File oldFile = new File(oldPath);
        boolean deleted = oldFile.delete();
        if (deleted) {
            try {
                return PaperStatus.SUCCESS;
            }catch (Exception ex){
                throw new PaperException("delele the old file error!.", ex, PaperStatus.DELETE_OLD_PAPER_ERROR);
            }
        }
    }
}