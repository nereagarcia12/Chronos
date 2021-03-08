package com.chronos.edgeservice.security;

import com.chronos.edgeservice.client.UserClient;
import com.chronos.edgeservice.security.dto.User;
import com.chronos.edgeservice.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userClient.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));

        return UserDetailsImpl.build(user);
    }

}
