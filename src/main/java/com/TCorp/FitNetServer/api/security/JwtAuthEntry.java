//package com.TCorp.FitNetServer.api.security;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import java.io.IOException;
//
///**
// * File: JwtAuthEntry
// * Author: turnernaef
// * Date: 11/1/23
// * Description: This class is used to handle unauthorized requests.
// */
//
//public class JwtAuthEntry implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        response.sendError( HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//    }
//}
