package com.group3.courseenrollment.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private List<Role> roles = new ArrayList<>();
}
