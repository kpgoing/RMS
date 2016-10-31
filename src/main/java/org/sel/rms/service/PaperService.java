package org.sel.rms.service;
import org.sel.rms.entity.PaperEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
* 生成于2016/10/29
*/
public interface PaperService {

    public void publishPaper(PaperEntity paperEntity);

    public void modifyPaper(PaperEntity paperEntity);

    public void deletePaper(int idPaper, int idTeacher);

    public PaperEntity getPaperById(int id);

    public Page<PaperEntity> getPageEntitiesByIdOfTeacher(int id, Pageable pagea);

}