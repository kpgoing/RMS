package org.sel.rms.service.impl;
import ch.qos.logback.core.status.Status;
import org.apache.commons.lang3.StringUtils;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import static com.sun.tools.doclint.Entity.nu;
import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;


/**
* 生成于2016/10/29
*/
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    PaperRepository paperRepository;


    @Value("${config.teacher.key}")
    String teacherKey;

    @Override
    public void publishPaper(PaperEntity paperEntity) {
        LocalDate localDate = LocalDate.now();
        paperEntity.setPublishDate(Date.valueOf(localDate));
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
    public Page<PaperEntity> getPageEntitiesByTitle(String title, Pageable pageable) {
        Page<PaperEntity> paperEntities = null;
//        try {
//            paperEntities = paperRepository.findLikeTitle(title, pageable);
//        } catch (Exception ex) {
//            throw new PaperException("get entities by paper title error who title like " + title, ex, PaperStatus.DATABASE_ERROR);
//        }
        return paperEntities;
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
        paperEntity.setPublishDate(found.getPublishDate());
        paperRepository.save(paperEntity);
    }

    @Override
    public void deletePaper(int idPaper, int idTeacher, HttpServletRequest request) {
        PaperEntity paperEntity = paperRepository.findOne(idPaper);
        if (paperEntity.getIdTeacher() == idTeacher) {
            try {
                deleteFile(request, paperEntity.getContent());
                paperRepository.delete(idPaper);

            } catch (Exception ex) {
                throw new PaperException("delete paper error which id = " + idPaper, ex, PaperStatus.NOT_FOUND);
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
        String extensionName = fileName.substring(fileName.indexOf("."));
        fileName = fileName.substring(0,fileName.indexOf("."));
        fileName = fileName + new java.util.Date().getTime() + extensionName;
        String path = request.getServletContext().getRealPath(File.separator + "upload") + File.separator + id ;

        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();
        try {
            path = path + File.separator + fileName;
            File realFile = new File(path);
            realFile.createNewFile();
            file.transferTo(realFile);
            sqPath = path.substring(path.indexOf(File.separator + "upload"));
        } catch (IOException e) {
            throw new PaperException("upload file error!", e, PaperStatus.UPLOAD_FILE_ERROR);
        }
        return sqPath;
    }


    @Override
    public PaperStatus deleteFile(HttpServletRequest request, String oldPath) {
        oldPath = request.getServletContext().getRealPath("") + oldPath;
        File oldFile = new File(oldPath);
        try {
        boolean deleted = oldFile.delete();
            if (deleted) {
                return PaperStatus.SUCCESS;
            } else {
                throw new PaperException("delele the old file error!.", PaperStatus.DELETE_OLD_FILE_ERROR);
            }
        }catch (Exception ex){
            throw new PaperException("delele the old file error!.", ex, PaperStatus.DELETE_OLD_FILE_ERROR);
        }

    }

    @Override
    public Page<PaperEntity> searchPaper(String keyWord, Pageable page) {
        Page<PaperEntity> paperEntities;
        String[] keyWords = keyWord.split(" ");
//        keyWord = StringUtils.join(keyWords, "|");
//        keyWord = "(" + keyWord + ")";
//        Arrays.fill(keyWords, keyWord);
        keyWord = StringUtils.join(keyWords, "%");
        keyWord = "%" + keyWord + "%";
        paperEntities = paperRepository.search(keyWord, page);
        return paperEntities;
    }
}