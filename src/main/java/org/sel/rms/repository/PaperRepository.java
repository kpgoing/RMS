package org.sel.rms.repository;
import org.sel.rms.entity.PaperEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
* 生成于2016/10/29
*/
public interface PaperRepository extends JpaRepository<PaperEntity,Integer> {
    Page<PaperEntity> findByIdTeacher(int id, Pageable pageable);
}