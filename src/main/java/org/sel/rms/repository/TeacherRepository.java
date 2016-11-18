package org.sel.rms.repository;
import org.sel.rms.entity.TeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
* 生成于2016/10/29
*/
public interface TeacherRepository extends JpaRepository<TeacherEntity,Integer> {
    TeacherEntity findByAccount(String name);

    @Query("select s from TeacherEntity s where  s.name like :kw")
    Page<TeacherEntity> search(@Param("kw") String kw, Pageable pageable);
}