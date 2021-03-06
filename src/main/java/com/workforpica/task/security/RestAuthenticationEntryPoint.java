package com.workforpica.task.security;

import com.workforpica.task.controller.payload.lobby.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger log = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        final String expiredToken = (String) httpServletRequest.getAttribute("custom-msg");
        String message = e.getMessage();
        if (expiredToken != null) message = expiredToken;

        log.error("Responding with unauthorized error. Message - {}", message);

        GenericResponse genericResponse = new GenericResponse(HttpStatus.UNAUTHORIZED.name(),message);
        sendResponse(httpServletResponse,genericResponse);
    }

    private void sendResponse(HttpServletResponse httpServletResponse, GenericResponse genericResponse) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        out.print(genericResponse);
        out.flush();
    }
}
