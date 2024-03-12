package org.dragon.dragonmath.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.dragon.dragonmath.jwt.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final JwtUtil jwtUtil;

    @GetMapping("")
    @Operation(summary = "ADMIN 권한 확인", description = "ADMIN 권한 확인")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "ADMIN 권한 확인",
                            content =
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    public ResponseEntity<?> getAdmin() {
        return ResponseEntity.ok().body(SecurityContextHolder.getContext().getAuthentication().getName() + " 님은 접근 권한을 부여받았습니다.");
    }

}
