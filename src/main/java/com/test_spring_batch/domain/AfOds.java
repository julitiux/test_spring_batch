package com.test_spring_batch.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "afods")
@Data
public class AfOds {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String codEnt;
  private String idListNegra;
  private String codListNegra;

  @CreationTimestamp
  private Date fchTimestampUmo;

}
