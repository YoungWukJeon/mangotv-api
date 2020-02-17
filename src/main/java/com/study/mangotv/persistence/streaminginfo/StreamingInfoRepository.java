package com.study.mangotv.persistence.streaminginfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamingInfoRepository extends JpaRepository<StreamingInfoEntity, String> {
    List<StreamingInfoEntity> findByTitleContaining(String title);
}
