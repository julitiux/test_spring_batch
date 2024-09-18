package com.test_spring_batch.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("AfOdsMongo")
public class AfOdsMongo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String codeEntity;
  private String idListBlack;
  private String codeListBlack;

}
