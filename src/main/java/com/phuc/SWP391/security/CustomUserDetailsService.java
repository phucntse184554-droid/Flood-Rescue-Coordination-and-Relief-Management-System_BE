//package com.phuc.SWP391.security;
//
//import com.phuc.SWP391.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.getUserByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//
//        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
//
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        authorities.add(authority);
//
////        return new org.springframework.security.core.userdetails.User(user.getId(),email, user.getPassword(), authorities);
//        return new CustomUserDetails(
//                user.getId(),
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
//    }
//}
