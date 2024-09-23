package com.voghbum.application.data.repository;

import com.voghbum.application.data.entity.UserStoriesEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserStoriesRepository extends CrudRepository<UserStoriesEntity, Long> {
    Optional<UserStoriesEntity> findByNickName(String nickName);
}
