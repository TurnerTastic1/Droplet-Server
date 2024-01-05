package com.TCorp.FitNetServer.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.Map;

/**
 * File: ResponseGlobal
 * Author: turnernaef
 * Date: 1/3/24
 * Description: This file contains the ResponseGlobal class, which is used to handle responses from the API.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGlobal {
    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("data")
    private Map<String, Object> data;
}
