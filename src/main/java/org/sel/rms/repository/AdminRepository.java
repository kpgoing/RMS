package org.sel.rms.repository;
import org.sel.rms.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
* 生成于2016/10/29
*/
public interface AdminRepository extends JpaRepository<AdminEntity,Integer> {
    AdminEntity findByAccount(String name);
}