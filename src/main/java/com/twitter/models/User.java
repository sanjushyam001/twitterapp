package com.twitter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private String gender;
    private List<Post> savedPost=new ArrayList<>();
    private List<Integer> followers;
    private List<Integer> followings;

}
