package com.github.flombois.services.users;

import com.github.flombois.repositories.LdapUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class LdapUserService implements UserDetailsService {

    private final String USER_NOT_FOUND = "User not found";

    private final LdapUserRepository ldapUserRepository;

    @Autowired
    public LdapUserService(LdapUserRepository ldapUserRepository) {
        this.ldapUserRepository = ldapUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(ldapUserRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }
}
