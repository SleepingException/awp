package org.goodie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {
  private UUID id;
  private String name;
  private String login;
  private String password;
  private boolean adminFlag;
  private boolean restrictionFlag;
}
