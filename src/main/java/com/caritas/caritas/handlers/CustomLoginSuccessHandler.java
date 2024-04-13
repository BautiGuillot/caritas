package com.caritas.caritas.handlers;

import com.caritas.caritas.model.Admin;
import com.caritas.caritas.service.AdminServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private AdminServiceImp adminService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Admin admin = (Admin) authentication.getPrincipal();
        if (admin.getFailedAttempt() > 0) {
            adminService.resetFailedAttempts(admin.getEmail());
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
