package com.ecommerce.backend.ecommercebackend.service;

import com.ecommerce.backend.ecommercebackend.entity.Role;
import com.ecommerce.backend.ecommercebackend.entity.User;
import com.ecommerce.backend.ecommercebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(usernameOrEmail)
                .or(() -> userRepository.findByEmail(usernameOrEmail))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + usernameOrEmail));

        if (Boolean.FALSE.equals(user.getIsActive())) {
            throw new UsernameNotFoundException("User is deactivated: " + usernameOrEmail);
        }

        Collection<? extends GrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .accountLocked(false)
                .accountExpired(false)
                .credentialsExpired(false)
                .disabled(Boolean.FALSE.equals(user.getIsActive()))
                .build();
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        if (Objects.isNull(roles)) return Set.of();
        return roles.stream()
                .map(Role::getName)
                .filter(Objects::nonNull)
                .map(name -> new SimpleGrantedAuthority("ROLE_" + name))
                .collect(Collectors.toSet());
    }
}


