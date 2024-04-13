package com.caritas.caritas.handlers;


import com.caritas.caritas.service.AdminService;
import com.caritas.caritas.model.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    //SimpleUrlAuthenticationFailureHandler
    /*
    * performs a redirect to the value of the defaultFailureUrl property when the onAuthenticationFailure method is called.
    * If the property has not been set it will send a 401 (Unauthorized) response to the client,
    * with the error message from the AuthenticationException which caused the failure.
    * */
    @Autowired
    private  AdminService adminService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String email = request.getParameter("username");
        System.out.println("---------------------------------------"+email);
        Admin admin  = (Admin) adminService.loadUserByUsername(email);

        if (admin != null) {
            if (admin.isEnabled() && admin.isAccountNonLocked()) {
                if (admin.getFailedAttempt() < adminService.MAX_FAILED_ATTEMPTS - 1) {
                    adminService.increaseFailedAttempts(admin);
                } else {
                    adminService.lock(admin);
                    exception = new LockedException("Su cuenta acaba de ser bloqueada por tener 3 intentos fallidos."
                            + " Automáticamente se desbloqueará en 24 hs.");
                }
            } else if (!admin.isAccountNonLocked()) {
                if (adminService.unlockWhenTimeExpired(admin)) {
                    exception = new LockedException("Su cuenta fue desbloqueada, intente loguearse nuevamente");
                }
            }

        }

        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }

}
