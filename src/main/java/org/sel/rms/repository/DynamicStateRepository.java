package org.sel.rms.repository;

import org.sel.rms.entity.DynamicStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by xubowei on 15/11/2016.
 */
public interface DynamicStateRepository extends JpaRepository<DynamicStateEntity,Integer> {

    @Modifying
    @Query("delete from DynamicStateEntity u where u.kind like ?1 and u.idContent = ?2")
    void deleteByKindAndIdContent(String uid, int kid);
}
