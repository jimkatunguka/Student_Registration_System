package com.group3.courseenrollment.service.impl;

import com.group3.courseenrollment.configuration.ApplicationProperties;
import com.group3.courseenrollment.domain.Credential;
import com.group3.courseenrollment.domain.Entry;
import com.group3.courseenrollment.domain.Role;
import com.group3.courseenrollment.domain.Student;
import com.group3.courseenrollment.repository.CredentialRepository;
import com.group3.courseenrollment.repository.EntryRepository;
import com.group3.courseenrollment.repository.StudentRepository;
import com.group3.courseenrollment.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CredentialServiceImpl implements CredentialService, UserDetailsService {

    @Autowired
    private CredentialRepository credentialRepository;


    @Autowired
    private ApplicationProperties properties;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Credential> optionalCredential = credentialRepository.findByUsername(username);
        optionalCredential.orElseThrow(()->new UsernameNotFoundException("User not found in our system"));

        List<GrantedAuthority> mapRolesToGrantedAuthorities = optionalCredential.get()
                .getRoles()
                .stream()
                .map(Role::getRole)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new User(optionalCredential.get().getUsername(),optionalCredential.get().getPassword(),mapRolesToGrantedAuthorities);
    }
}
