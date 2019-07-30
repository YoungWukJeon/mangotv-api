package com.study.mangotv.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findBySrl(long srl);
}