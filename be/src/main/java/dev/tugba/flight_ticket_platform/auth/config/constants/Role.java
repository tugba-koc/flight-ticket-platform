package dev.tugba.flight_ticket_platform.auth.config.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum Role {
  
  USER(Set.of(
          Permission.USER_READ,
          Permission.USER_UPDATE,
          Permission.USER_DELETE,
          Permission.USER_CREATE
 )),
  ADMIN(Set.of(
          Permission.ADMIN_READ,
          Permission.ADMIN_UPDATE,
          Permission.ADMIN_DELETE,
          Permission.ADMIN_CREATE
  )),
  VISITOR(Set.of(
          Permission.VISITOR_READ,
          Permission.VISITOR_UPDATE,
          Permission.VISITOR_DELETE,
          Permission.VISITOR_CREATE
  ));

  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
      List<SimpleGrantedAuthority> authorities = getPermissions().stream()
          .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
          .collect(Collectors.toList());
      authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
      return authorities;
  }
}
