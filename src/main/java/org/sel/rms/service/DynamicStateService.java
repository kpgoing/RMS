package org.sel.rms.service;

import org.sel.rms.entity.DynamicStateEntity;
import org.sel.rms.entity.PaperEntity;
import org.sel.rms.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by xubowei on 15/11/2016.
 */

public interface DynamicStateService {

    public void addPaper(PaperEntity paperEntity);

    public void addProject(ProjectEntity projectEntity);

    public Page<DynamicStateEntity> getByPage(Pageable pageable);

    public void deleteByKindAndIdContent(String kind, int idContent);
}
