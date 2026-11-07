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
@Table(name = "application", schema = "jobspot")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private Internship internship;

    @Column(name = "cover_letter")
    private String coverLetter;

    @ManyToOne
    @JoinColumn(name = "cv_id")
    private Cv cv;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status;

    @Column(name = "applied_at")
    private LocalDate appliedAt;

    @OneToMany
    @JoinColumn(name = "application_id")
    private Set<Interview> interviews = new LinkedHashSet<>();

}