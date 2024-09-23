package com.voghbum.application.data.repository;

import com.voghbum.application.data.entity.UserProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserProfileRepository extends CrudRepository<UserProfileEntity, Long> {
    Optional<UserProfileEntity> findByNickName(String nickName);
}
