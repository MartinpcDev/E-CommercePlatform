package com.martin.api.utils;

import com.martin.api.persistence.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtils {

  public static Long extractId(UserDetails userDetails) {
    return ((User) userDetails).getId();
  }
}
