package com.study.mangotv.persistence.streamingviewernum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingViewerNumRepository extends JpaRepository<StreamingViewerNumEntity, String> {
}
