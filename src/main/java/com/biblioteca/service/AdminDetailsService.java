package com.biblioteca.service;

import com.biblioteca.model.Admin;
import com.biblioteca.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsService implements UserDetailsService {
    @Autowired private AdminRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin nao encontrado: " + email));
        return User.builder()
                .username(admin.getEmail())
                .password(admin.getSenha())
                .roles("ADMIN")
                .build();
    }
}
