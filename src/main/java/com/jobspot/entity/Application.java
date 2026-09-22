package com.jobspot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "application", schema = "jobspot")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn
    private User student;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private Internship internship;

    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "cv")
    private String cv;

    @Column(name = "status")
    private String status;

    @Column(name = "applied_at")
    private Instant appliedAt;

    @OneToMany(mappedBy = "application")
    private Set<Interview> interviews = new LinkedHashSet<>();

}