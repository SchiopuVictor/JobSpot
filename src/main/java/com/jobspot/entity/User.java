package com.jobspot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user", schema = "jobspot")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "active")
    private Boolean active = false;

    @OneToMany
    @JoinColumn(name = "student_id")
    private Set<Application> applications = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Company> companies = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "student_id")
    private Set<Cv> cvs = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "student_id")
    private Set<Evaluation> evaluations = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Notification> notifications = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Student> students = new LinkedHashSet<>();

}