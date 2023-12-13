package com.twitter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class User {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
