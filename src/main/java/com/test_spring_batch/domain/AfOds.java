package com.test_spring_batch.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("AfOds")
@Data
public class AfOds {

  @Id
  private String id;
  private String codeEntity;
  private String idListBlack;
  private String codeListBlack;

}
