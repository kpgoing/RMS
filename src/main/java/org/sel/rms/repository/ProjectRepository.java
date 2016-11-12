package org.sel.rms.repository;
import org.sel.rms.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
* 生成于2016/10/29
*/
public interface ProjectRepository extends JpaRepository<ProjectEntity,Integer> {
    Page<ProjectEntity> findByIdTeacher(int id, Pageable pageable);

    @Query("select s from ProjectEntity s where  s.introduction like :kw or s.master like  :kw or s.name like :kw or s.source like :kw")
    Page<ProjectEntity> search(@Param("kw")String kw, Pageable pageable);
}