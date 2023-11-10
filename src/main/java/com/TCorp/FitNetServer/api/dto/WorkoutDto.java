package com.TCorp.FitNetServer.api.dto;

import lombok.Data;

/**
 * File: WorkoutDto
 * Author: turnernaef
 * Date: 11/10/23
 * Description: This class is used to transfer workout information from the client to the server.
 */

@Data
public class WorkoutDto {
    private String name;
    private String workoutType;
    private String description;
    private String date;

    private Integer duration;
    private Integer distance;
}
