package com.uz.Repository;

import com.uz.Entity.Korres;
import com.uz.Entity.Tsj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TsjRepository extends JpaRepository<Tsj, Integer> {

    List<Tsj> findByTsjNameIsNotLike(String name);

}
