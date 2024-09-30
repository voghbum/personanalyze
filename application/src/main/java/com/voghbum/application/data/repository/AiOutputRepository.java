package com.voghbum.application.data.repository;

import com.voghbum.application.data.entity.AiOutputEntity;
import com.voghbum.application.data.entity.AiOutputType;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface AiOutputRepository extends CrudRepository<AiOutputEntity, Long> {
    Optional<AiOutputEntity> findByUsernameAndResultType(String username, AiOutputType outputType);
}
