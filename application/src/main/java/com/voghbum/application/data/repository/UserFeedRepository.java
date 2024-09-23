package com.voghbum.application.data.repository;

import com.voghbum.application.data.entity.UserFeedEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserFeedRepository extends CrudRepository<UserFeedEntity, Long> {
    Optional<UserFeedEntity> findByNickName(String nickName);
}
