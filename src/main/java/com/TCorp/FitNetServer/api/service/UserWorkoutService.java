package com.TCorp.FitNetServer.api.service;

import com.TCorp.FitNetServer.api.dto.WorkoutDto;
import com.TCorp.FitNetServer.api.exception.CustomException;
import com.TCorp.FitNetServer.api.model.CompletedWorkout;
import com.TCorp.FitNetServer.api.repository.CompletedWorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * File: UserWorkoutService
 * Author: turnernaef
 * Date: 11/10/23
 * Description: This class is used to handle business logic related to user workouts.
 */

@Service
@RequiredArgsConstructor
public class UserWorkoutService {

    private final CompletedWorkoutRepository completedWorkoutRepository;

    public ResponseEntity<Map<String, Object>> getAllUserWorkouts() {
        try {
            List<CompletedWorkout> completedWorkouts = completedWorkoutRepository.findAll();
            return ResponseEntity.ok(Map.of("message", "Workouts fetched successfully", "Workouts", completedWorkouts));
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fetch workouts from database");
        }
    }

    public ResponseEntity<Map<String, Object>> completeWorkout(WorkoutDto workoutDto) {
        List<String> errors = new ArrayList<>();
        if (workoutDto == null) {
            errors.add("Dto is required");
        }

        if (!errors.isEmpty()) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in logic before user save", errors);
        }

        CompletedWorkout completedWorkout = new CompletedWorkout();
        completedWorkout.setName(workoutDto.getName());
        completedWorkout.setWorkoutType(workoutDto.getWorkoutType());
        completedWorkout.setDescription(workoutDto.getDescription());
        completedWorkout.setDate(workoutDto.getDate());
        completedWorkout.setDuration(workoutDto.getDuration());
        completedWorkout.setDistance(workoutDto.getDistance());

        try {
            completedWorkoutRepository.save(completedWorkout);
            return ResponseEntity.ok(Map.of("message", "Workout completed! Successfully saved to database."));
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save user", errors);
        }
    }
}
