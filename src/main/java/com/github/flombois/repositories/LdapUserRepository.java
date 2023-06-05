package com.github.flombois.repositories;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface LdapUserRepository extends LdapRepository<UserDetails> {

    UserDetails findByUsername(String username);

}
