package com.github.flombois.services.users;

import com.github.flombois.entities.User;
import com.github.flombois.repositories.LdapUserRepository;
import com.github.flombois.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final LdapUserRepository ldapUserRepository;
    private final UserRepository userRepository;

    private final ApplicationContext applicationContext;

    @Autowired
    public UserServiceImpl(LdapUserRepository ldapUserRepository, UserRepository userRepository,
                           ApplicationContext applicationContext) {
        this.ldapUserRepository = ldapUserRepository;
        this.userRepository = userRepository;
        this.applicationContext = applicationContext;
    }

    @Override
    public void registerUser(String username) {
        // First check that user already exist in the LDAP repository
        Optional<UserDetails> userDetails = Optional.ofNullable(ldapUserRepository.findByUsername(username));

        // If the user is present in the LDAP repository and hasn't yet been registered
        // then create a copy in the database
        if (userDetails.isPresent() && !userRepository.existsByUsername(username)) {
             userRepository.save(toUser(userDetails.get()));
        }
    }

    /**
     * Convert a {@link UserDetails} object to a {@link User}
     * @param userDetails The user details provided by the LDAP server
     * @return A new instance of {@link User}
     */
    private User toUser(UserDetails userDetails) {
        return applicationContext.getBean(User.class, userDetails);
    }

    @Override
    public Optional<User> fetchUser(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public Optional<User> fetchUser(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        for (User user : userRepository.findAll()) users.add(user);
        return users;
    }
}
