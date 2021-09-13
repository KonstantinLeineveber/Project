package com.example.servingwebcontent.config.security;

import com.example.servingwebcontent.user.User;
import com.example.servingwebcontent.repository.userRepository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(s).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
//        user.setBookmarksEmpty(user.getBookmarks().isEmpty());
        return new SecurityUser(user);
    }
}
