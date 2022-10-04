package com.example.socialnet.Service;

import com.example.socialnet.Entities.User;
import com.example.socialnet.Repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
private  final UserRepo userRepo;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional=userRepo.findByUsername(username);
        User user=userOptional.orElseThrow(()->new UsernameNotFoundException(username+" is not found "));

        return new org.springframework.security.core.userdetails.User(user.getUsername()
                , user.getPassword(), user.isEnabled(), true,true, true, getAuthorities("USER"));
    }
    public Collection<GrantedAuthority> getAuthorities(String role){
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
