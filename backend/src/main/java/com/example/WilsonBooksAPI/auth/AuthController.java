package com.example.WilsonBooksAPI.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService service;

  @PostMapping("/signup")
  public ResponseEntity<AuthResponse> signup(
      @RequestBody AccountRequest request
  ) {
    return ResponseEntity.ok(service.signup(request));
  }
  @PostMapping("/signin")
  public ResponseEntity<AuthResponse> signin(
      @RequestBody LogRequest request
  ) {
    return ResponseEntity.ok(service.signin(request));
  }


}
