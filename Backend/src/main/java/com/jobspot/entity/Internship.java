package com.jobspot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "internship", schema = "jobspot")
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "responsibilities")
    private String responsibilities;

    @Column(name = "location")
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 200)
    private InternshipType type;

    @Column(name = "duration")
    private Instant duration;

    @Column(name = "available_positions")
    private String availablePositions;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "application_deadline")
    private Instant applicationDeadline;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InternshipStatus status;

    @OneToMany
    @JoinColumn(name = "internship_id")
    private Set<Application> applications = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "internship_id")
    private Set<Evaluation> evaluations = new LinkedHashSet<>();

}