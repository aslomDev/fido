package com.uz.Repository;

import com.uz.Entity.Korres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KorresRepository extends JpaRepository<Korres, Integer> {

    List<Korres> findByKorNameIsNotLike(String name);

}
