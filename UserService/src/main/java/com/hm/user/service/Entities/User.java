package com.hm.user.service.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "ID")
    private String userId ;

    @Column(name = "NAME" , length = 20)
    private String name ;

    @Column(name = "EMAIL")
    private String email ;

    @Column(name = "ABOUT")
    private String about ;

    @Transient
    private List<Rating> ratings = new ArrayList<>();



}
