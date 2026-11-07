package com.jobspot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "evaluation", schema = "jobspot")
public class Evaluation {
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

    @Column(name = "technical_skills")
    private String technicalSkills;

    @Column(name = "communication")
    private String communication;

    @Column(name = "teamwork")
    private String teamwork;

    @Column(name = "responsibility")
    private String responsibility;

    @Column(name = "overall_rating")
    private Integer overallRating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at")
    private LocalDate createdAt;

}