package org.sel.rms.service.impl;
import org.sel.rms.entity.PaperEntity;
import org.sel.rms.exception.PaperException;
import org.sel.rms.repository.PaperRepository;
import org.sel.rms.service.PaperService;
import org.sel.rms.status.PaperStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        PaperEntity paperEntity = paperRepository.getOne(id);
        return paperEntity;
    }

    @Override
    public void modifyPaper(PaperEntity paperEntity) {
        paperRepository.save(paperEntity);
    }

    @Override
    public void deletePaper(int id) {
        paperRepository.delete(id);
    }
}