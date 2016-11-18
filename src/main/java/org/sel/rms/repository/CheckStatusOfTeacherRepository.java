package org.sel.rms.repository;
import org.sel.rms.entity.CheckStatusOfTeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
* 生成于2016/10/29
*/
public interface CheckStatusOfTeacherRepository extends JpaRepository<CheckStatusOfTeacherEntity,Integer> {

    CheckStatusOfTeacherEntity findByidTeacher(int teacherId);

    @Query("select c from CheckStatusOfTeacherEntity c where c.checkStatus = 0 ")
    List<CheckStatusOfTeacherEntity> uncheckList();
}