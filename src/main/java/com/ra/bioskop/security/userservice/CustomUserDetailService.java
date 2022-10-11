package com.ra.bioskop.security.userservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ra.bioskop.exception.BioskopException;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.model.user.Users;
import com.ra.bioskop.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findUserByEmail(email);
        if(!user.isPresent())
            throw BioskopException.throwException(
                ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND, "User dengan email "+email+" tidak ditemukan!");
        return new ApplicationUser(user.get());
    }
    
}
