package com.TCorp.FitNetServer.api.routes.userWorkouts;

import com.TCorp.FitNetServer.api.dto.CompletedWorkoutDto;
import com.TCorp.FitNetServer.api.response.ResponseGlobal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseGlobal> getAllUserWorkouts() {
        return ResponseEntity.ok(
                ResponseGlobal.builder()
                        .code(200)
                        .message("Successfully fetched all user workouts")
                        .status(true)
                        .timestamp(System.currentTimeMillis())
                        .data(userWorkoutService.getAllUserWorkouts())
                        .build()
        );
    }

    @GetMapping("/user-workouts")
    public ResponseEntity<ResponseGlobal> getUserWorkouts() {
        return ResponseEntity.ok(
                ResponseGlobal.builder()
                        .code(200)
                        .message("Successfully fetched all user workouts")
                        .status(true)
                        .timestamp(System.currentTimeMillis())
                        .data(userWorkoutService.getUserWorkouts())
                        .build()
        );
    }

    @PostMapping("/complete-workout")
    public ResponseEntity<ResponseGlobal> completeWorkout(@Valid @RequestBody CompletedWorkoutDto completedWorkoutDto) {
        return ResponseEntity.ok(
                ResponseGlobal.builder()
                        .code(200)
                        .message("Successfully completed workout")
                        .status(true)
                        .timestamp(System.currentTimeMillis())
                        .data(userWorkoutService.completeWorkout(completedWorkoutDto))
                        .build()
        );
    }
}
