package com.jobspot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "internship", schema = "jobspot")
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private User company;

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

    @Column(name = "type")
    private String type;

    @Column(name = "duration")
    private Instant duration;

    @Column(name = "available_positions")
    private String availablePositions;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "application_deadline")
    private Instant applicationDeadline;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "internship")
    private Set<Application> applications = new LinkedHashSet<>();

}