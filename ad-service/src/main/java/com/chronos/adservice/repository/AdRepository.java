package com.chronos.adservice.repository;

import com.chronos.adservice.enums.Status;
import com.chronos.adservice.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad,Integer> {

    public List<Ad> findByStatus(Status status);
    public List<Ad> findByTitleContains(String word);
    public List<Ad> findByCategory_Id(Integer categoryId);
    public List<Ad> findByTitleContainsAndCategory_Id(String word, Integer id);
    public List<Ad> findByUserId(Integer id);
}
