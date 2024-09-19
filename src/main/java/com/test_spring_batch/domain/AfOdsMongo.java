package com.test_spring_batch.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("AfOdsMongo")
@Data
public class AfOdsMongo {

  @Id
  private String id;
  private String codeEntity;
  private String idListBlack;
  private String codeListBlack;

}
