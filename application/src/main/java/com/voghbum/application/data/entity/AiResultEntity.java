package com.voghbum.application.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ai_result", indexes = {
        @Index(name = "idx_id_result_type", columnList = "id, resultType")
})
public class AiResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private AiResultType resultType;

    @Column(columnDefinition = "TEXT")
    private String aiResult;
}
