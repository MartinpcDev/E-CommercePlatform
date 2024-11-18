package com.martin.api.utils;

import java.util.Arrays;
import java.util.List;

public enum UserRole {
  ADMIN(Arrays.asList(Permissions.values())),
  USER(Arrays.asList(Permissions.values()));

  private List<Permissions> permissions;

  UserRole(List<Permissions> permissions) {
    this.permissions = permissions;
  }

  public List<Permissions> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<Permissions> permissions) {
    this.permissions = permissions;
  }
}
