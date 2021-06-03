package com.chronos.adservice.repository;

import com.chronos.adservice.dto.FavoriteResponseDto;
import com.chronos.adservice.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    public Optional<Favorite> findByAdIdAndUserId(Integer adId, Integer userId);
    public List<Favorite> findByUserId(Integer userId);
    public void deleteByAdId(Integer adId);
}
