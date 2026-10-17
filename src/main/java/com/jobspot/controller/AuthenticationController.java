package com.jobspot.controller;

import com.jobspot.config.JwtConfig;
import com.jobspot.dto.AuthRequestDto;
import com.jobspot.dto.JwtResponse;
import com.jobspot.entity.Company;
import com.jobspot.entity.Role;
import com.jobspot.entity.Student;
import com.jobspot.entity.User;
import com.jobspot.exceptions.CompanyNotFoundException;
import com.jobspot.exceptions.StudentNotFoundException;
import com.jobspot.exceptions.UserNotFoundExceptions;
import com.jobspot.mapper.CompanyMapper;
import com.jobspot.mapper.StudentMapper;
import com.jobspot.repository.CompanyRepository;
import com.jobspot.repository.StudentRepository;
import com.jobspot.repository.UserRepository;
import com.jobspot.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JwtConfig jwtConfig;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody AuthRequestDto request,
            HttpServletResponse response)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundExceptions::new);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken",refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(jwtConfig.getRefreshTokenTime());
        cookie.setSecure(true);

        response.addCookie(cookie);

        return ResponseEntity.ok(new JwtResponse(accessToken));
    }

    @PostMapping("/validate")
    public boolean validateToken(@RequestHeader("Authorization") String headerToken){

        String token = headerToken.replace("Bearer ","");

         return jwtService.validateToken(token);

    }

    @GetMapping("/me")
    public ResponseEntity<?> identifyUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;

        Long userId = (Long)authentication.getPrincipal();

        User user = userRepository.findById(userId)
                .orElse(null);

        if (user == null){
            return ResponseEntity.notFound().build();
        }

        Role userRole = user.getRole();

        if(userRole.equals(Role.Student)){
            Student student = studentRepository.findByUserId(user.getId())
                    .orElseThrow(StudentNotFoundException::new);
            return ResponseEntity.ok(StudentMapper.toDto(student));
        }

        Company company = companyRepository.findByUserId(user.getId())
                .orElseThrow(CompanyNotFoundException::new);


        return ResponseEntity.ok(CompanyMapper.toDto(company));

    }


}
