package com.TCorp.FitNetServer.api.dto;

import com.TCorp.FitNetServer.api.model.WorkoutType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * File: WorkoutDto
 * Author: turnernaef
 * Date: 11/10/23
 * Description: This class is used to transfer workout information from the client to the server.
 */

@Data
public class CompletedWorkoutDto {
    @NotNull(message = "Workout name cannot be null")
    @Size(min = 1, max = 50, message = "Workout name must be between 1 and 50 characters")
    private String name;

    @NotNull(message = "Workout type cannot be null")
    private WorkoutType workoutType;

    @NotNull(message = "Workout description cannot be null")
    @Size(min = 1, max = 200, message = "Workout description must be between 1 and 200 characters")
    private String description;

    @NotNull(message = "Workout date cannot be null")
    @Size(min = 1, max = 20, message = "Workout date must be between 1 and 20 characters")
    private String date;

    @NotNull(message = "Workout duration cannot be null")
    private Double duration;

    private Integer distance;
}
