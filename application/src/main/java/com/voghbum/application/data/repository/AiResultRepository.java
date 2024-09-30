package com.voghbum.application.data.repository;

import com.voghbum.application.data.entity.AiResultEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface AiResultRepository extends CrudRepository<AiResultEntity, Long> {
    Optional<AiResultEntity> findByUsernameAndResultType(String username, long resultType);
}
