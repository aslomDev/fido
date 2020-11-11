package com.uz.Repository;

import com.uz.Entity.Cb;
import com.uz.Entity.Gni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CbRepository extends JpaRepository<Cb, Integer> {


    @Query(value = " SELECT * from zadacha.cb where month(cb_date) = month(current_date()) ", nativeQuery = true)
    List<Cb> findAll();


}
