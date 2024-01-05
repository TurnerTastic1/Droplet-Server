package com.TCorp.FitNetServer.api.model;

import com.TCorp.FitNetServer.api.dto.CompletedWorkoutDto;
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
    private UserEntity user;

    private String name;
    private WorkoutType workoutType;
    private String description;
    private String date;

    private Double duration;
    private Integer distance;


    public static CompletedWorkout createCompletedWorkoutObject(CompletedWorkoutDto completedWorkoutDto) {
        CompletedWorkout completedWorkout = new CompletedWorkout();
        completedWorkout.setName(completedWorkoutDto.getName());
        completedWorkout.setWorkoutType(completedWorkoutDto.getWorkoutType());
        completedWorkout.setDescription(completedWorkoutDto.getDescription());
        completedWorkout.setDate(completedWorkoutDto.getDate());
        completedWorkout.setDuration(completedWorkoutDto.getDuration());
        completedWorkout.setDistance(completedWorkoutDto.getDistance());
        return completedWorkout;
    }

}
