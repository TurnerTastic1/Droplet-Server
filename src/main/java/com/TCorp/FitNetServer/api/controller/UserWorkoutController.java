package com.TCorp.FitNetServer.api.controller;

import com.TCorp.FitNetServer.api.dto.WorkoutDto;
import com.TCorp.FitNetServer.api.service.UserWorkoutService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * File: UserWorkoutController
 * Author: turnernaef
 * Date: 11/10/23
 * Description: This class is used to handle requests related to user workouts. ie. a user's workout history, current workout, etc.
 */

@RestController
@RequestMapping(path = "/FitNetServer/api/v1/user-workout")
@RequiredArgsConstructor
public class UserWorkoutController {

    private final UserWorkoutService userWorkoutService;

    @GetMapping("/get-all-user-workouts")
    public ResponseEntity<Map<String, Object>> getAllUserWorkouts() {
        return ResponseEntity.ok(Map.of("message", "Hello World from user workout controller!"));
    }

    @PostMapping("/complete-workout")
    public ResponseEntity<Map<String, Object>> completeWorkout(@RequestBody WorkoutDto workoutDto) {
        return userWorkoutService.completeWorkout(workoutDto);
    }
}
