package com.example.WilsonBooksAPI.auth;

import com.example.WilsonBooksAPI.appConfig.JwtService;
import com.example.WilsonBooksAPI.appError.NotAuthorizedException;
import com.example.WilsonBooksAPI.AuthToken.Token;
import com.example.WilsonBooksAPI.AuthToken.TokenRepository;
import com.example.WilsonBooksAPI.AuthToken.TokenType;
import com.example.WilsonBooksAPI.user.User;
import com.example.WilsonBooksAPI.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthResponse signup(AccountRequest request) {

    var user = repository.findByEmail(request.getEmail());
    if(user.isPresent()){
      throw new NotAuthorizedException("Email already taken");
    }
    var newUser = User.builder()
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();
    var savedUser = repository.save(newUser);
    var jwtToken = jwtService.generateToken(newUser);
    saveUserToken(savedUser, jwtToken);

    AuthResponse response = new AuthResponse();
    response.setAccessToken(jwtToken);
    response.setUser(savedUser.getId());
    response.setEmail(savedUser.getEmail());
    response.setUsername(savedUser.getName());
    return response;

  }

  public AuthResponse signin(LogRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);

    AuthResponse response = new AuthResponse();
    response.setAccessToken(jwtToken);
    response.setUser(user.getId());
    response.setEmail(user.getEmail());
    response.setUsername(user.getName());
    return response;
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(Math.toIntExact(user.getId()));
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

}
