package com.example.WilsonBooksAPI.auth;

import com.example.WilsonBooksAPI.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

  private String username;
  private String email;
  private String password;
  private Role role;
}
