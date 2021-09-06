package org.javajarvis.SistemCuti_UASJava.controller;

import org.javajarvis.SistemCuti_UASJava.dto.LoginDto;
import org.javajarvis.SistemCuti_UASJava.model.Employee;
import org.javajarvis.SistemCuti_UASJava.repository.EmployeeRepository;
import org.javajarvis.SistemCuti_UASJava.service.AuthService;
import org.javajarvis.SistemCuti_UASJava.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthenticationManager authenticationManager;
    EmployeeDetailsService employeeDetailsService;
    AuthService authService;
    EmployeeRepository employeeRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, EmployeeDetailsService employeeDetailsService, AuthService authService, EmployeeRepository employeeRepository) {
        this.authenticationManager = authenticationManager;
        this.employeeDetailsService = employeeDetailsService;
        this.authService = authService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/check")
    private ResponseEntity<?> check(@RequestParam String id) {
        Employee employee = employeeRepository.getByNipUsernameOrEmail(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("NIP, nama pengguna, atau email tidak valid.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Employee employee = employeeRepository.getByNipUsernameOrEmail(loginDto.getUsername());
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pengguna tidak ditemukan.");
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            UserDetails userDetails = employeeDetailsService.loadUserByUsername(loginDto.getUsername());
            String token = authService.createToken(userDetails.getUsername());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Kata sandi salah.");
        }
    }

    @GetMapping("/me")
    private ResponseEntity<?> me(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        String jwt = null;
        String username = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = authService.getIssuer(jwt);
        }

        Employee employee = null;

        if (username != null) {
            employee = employeeRepository.getByNipUsernameOrEmail(username);
        }

        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token tidak valid.");
        }
    }
}
