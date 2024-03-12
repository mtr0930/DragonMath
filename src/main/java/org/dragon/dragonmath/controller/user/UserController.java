package org.dragon.dragonmath.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.dragon.dragonmath.dto.User.CreateUserRequestDto;
import org.dragon.dragonmath.dto.User.GetUserResponseDto;
import org.dragon.dragonmath.jwt.JwtUtil;
import org.dragon.dragonmath.service.User.CustomUserDetailsService;
import org.dragon.dragonmath.service.User.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    @Operation(summary = "유저 정보 조회", description = "유저 ID로 유저 정보를 조회합니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                    description = "유저 정보 조회",
                    content =
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GetUserResponseDto.class)))
            }
    )
    public ResponseEntity<?> retrieveUserDetail(@RequestBody String id) {
        return ResponseEntity.ok().body(new GetUserResponseDto());
    }

    @PostMapping("/join")
    @Operation(summary = "신규 유저 정보 생성", description = "신규 유저 정보를 생성합니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "신규 유저 정보 생성",
                            content =
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto request) {
        userService.creatUser(request);
        return ResponseEntity.ok().body("create User 성공");
    }

}
