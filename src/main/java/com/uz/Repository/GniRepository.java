package com.uz.Repository;

import com.uz.Entity.Gni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
public interface GniRepository extends JpaRepository<Gni, Integer> {
//
//    SELECT something FROM tbl_name
//    -> WHERE DATE_SUB(CURDATE(),INTERVAL 30 DAY) <= date_col;


//    @Query("SELECT e FROM gni WHERE gni_date <= DATE_SUB(NOW(), INTERVAL 4 MONTH)")
//    @Query(" SELECT DATE_SUB("2017-06-15", INTERVAL -2 MONTH ")


    @Query(value = " select * from gni where gni_date <= date_sub(CURRENT_DATE(),INTERVAL 7 MONTH )  and CURRENT_DATE() ", nativeQuery = true)
    List<Gni> findAll();
    Iterator<Gni>findByGniDateBetween(Date date1, Date date2);







}
