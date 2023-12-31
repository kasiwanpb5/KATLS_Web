package KALTS.DoAnWebJava.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/error")
public class CustomErrorController {
    @GetMapping
    public String handleError(@NotNull HttpServletRequest request) {
        return Optional
                .ofNullable(request.getAttribute(
                        RequestDispatcher.ERROR_STATUS_CODE))
                .map(status -> Integer.parseInt(status.toString()))
                .filter(status -> status == 404
                        || status == 500
                        || status == 403)
                .map(status -> "error/" + status)
                .orElse(null);
    }
}
