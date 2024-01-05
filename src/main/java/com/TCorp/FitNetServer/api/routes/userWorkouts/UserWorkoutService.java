package com.TCorp.FitNetServer.api.routes.userWorkouts;

import com.TCorp.FitNetServer.api.dto.CompletedWorkoutDto;
import com.TCorp.FitNetServer.api.exception.CustomException;
import com.TCorp.FitNetServer.api.model.CompletedWorkout;
import com.TCorp.FitNetServer.api.repository.CompletedWorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    public Map<String, Object> getAllUserWorkouts() {
        try {
            List<CompletedWorkout> completedWorkouts = completedWorkoutRepository.findAll();
            return (Map.of("Workouts", completedWorkouts));
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fetch workouts from database");
        }
    }

    public Map<String, Object> completeWorkout(CompletedWorkoutDto completedWorkoutDto) {
        List<String> errors = new ArrayList<>();
        if (completedWorkoutDto == null) {
            errors.add("Dto is required");
        }

        CompletedWorkout completedWorkout = new CompletedWorkout();
        completedWorkout.setName(completedWorkoutDto.getName());
        completedWorkout.setWorkoutType(completedWorkoutDto.getWorkoutType());
        completedWorkout.setDescription(completedWorkoutDto.getDescription());
        completedWorkout.setDate(completedWorkoutDto.getDate());
        completedWorkout.setDuration(completedWorkoutDto.getDuration());
        completedWorkout.setDistance(completedWorkoutDto.getDistance());

        if (!errors.isEmpty()) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in logic before user save", errors);
        }

        errors = new ArrayList<>();
        try {
            completedWorkoutRepository.save(completedWorkout);
            return Map.of("message", "Workout completed! Successfully saved to database.");
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save user", errors);
        }
    }
}
