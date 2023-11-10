package com.TCorp.FitNetServer.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * File: CompletedWorkouts
 * Author: turnernaef
 * Date: 11/10/23
 * Description: This class is used to define the completed_workouts table in the database.
 */

@Getter
@Setter
@Entity
@Table(name = "completed_workouts")
@Data
@NoArgsConstructor
public class CompletedWorkout {

    @Id
    @SequenceGenerator(
            name = "completed_workouts_sequence",
            sequenceName = "completed_workouts_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "completed_workouts_sequence"
    )
    @Column(name = "completed_workout_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userId;

    private String name;
    private WorkoutType workoutType;
    private String description;
    private String date;

    private Integer duration;
    private Integer distance;



}
