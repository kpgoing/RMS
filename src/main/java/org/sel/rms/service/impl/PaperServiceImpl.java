package org.sel.rms.service.impl;
import org.sel.rms.entity.PaperEntity;
import org.sel.rms.exception.PaperException;
import org.sel.rms.repository.PaperRepository;
import org.sel.rms.service.PaperService;
import org.sel.rms.status.PaperStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


/**
* 生成于2016/10/29
*/
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    PaperRepository paperRepository;

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
    public void modifyPaper(PaperEntity paperEntity) {
        PaperEntity found = getPaperById(paperEntity.getIdPaper());
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


}