package com.TCorp.FitNetServer.api.service;

import com.TCorp.FitNetServer.api.dto.WorkoutDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * File: UserWorkoutService
 * Author: turnernaef
 * Date: 11/10/23
 * Description: This class is used to handle business logic related to user workouts.
 */

@Service
public class UserWorkoutService {

    public ResponseEntity<Map<String, Object>> getAllUserWorkouts() {
        return ResponseEntity.ok(Map.of("message", "Hello World from user workout service!"));
    }

    public ResponseEntity<Map<String, Object>> completeWorkout(WorkoutDto workoutDto) {

        return ResponseEntity.ok(Map.of("message", "Workout completed! Successfully saved to database."));
    }
}
