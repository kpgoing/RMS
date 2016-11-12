package org.sel.rms.repository;
import org.sel.rms.entity.PaperEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
* 生成于2016/10/29
*/
public interface PaperRepository extends JpaRepository<PaperEntity,Integer> {
    Page<PaperEntity> findByIdTeacher(int id, Pageable pageable);

    @Query("select s from PaperEntity s where  s.title like :kw or s.writer like  :kw or s.abstractContent like :kw or s.keyWord like :kw")
//    @Query(value = "select * from PaperEntity where  UPPER(keyWord) like  UPPER(CONCAT('%',:kw,'%')) ",nativeQuery = true)
    Page<PaperEntity> search(@Param("kw") String keyword, Pageable pageable);


}