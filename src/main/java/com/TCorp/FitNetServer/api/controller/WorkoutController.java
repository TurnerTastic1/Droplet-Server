package com.TCorp.FitNetServer.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * File: WorkoutController
 * Author: turnernaef
 * Date: 11/10/23
 * Description: This class is used to handle requests related to workouts.
 */

@RestController
@RequestMapping(path = "/FitNetServer/api/v1/workout")
public class WorkoutController {

    @GetMapping("/get-all-workouts")
    public String getAllWorkouts() {
        return "Hello World from workout controller!";
    }


}
