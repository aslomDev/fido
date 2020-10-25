package com.uz.Repository;

import com.uz.Entity.Gni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GniRepository extends JpaRepository<Gni, Integer> {
//
//    SELECT something FROM tbl_name
//    -> WHERE DATE_SUB(CURDATE(),INTERVAL 30 DAY) <= date_col;


//    @Query("SELECT * FROM gni WHERE gni_date <= DATE_SUB(NOW(), INTERVAL 4 MONTH)")


    List<Gni> findAll();
}
