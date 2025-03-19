package com.metamorfoz.track_employee.common.auth;

import com.metamorfoz.track_employee.dao.UserDao;
import com.metamorfoz.track_employee.domain.User;
import com.metamorfoz.track_employee.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MyUserDetail implements UserDetailsService {
    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User '" + username + "' not found"));

        Set<UserRole> userRoleList = user.getUserRoleList();

        String [] roles= new String[userRoleList.size()];
        for (int i =0; i<userRoleList.size();i++) {
            roles[i]=userRoleList.iterator().next().getRole();
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(user.getPassword())//
                .authorities(roles)//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//   mail verification
                .build();
    }
}
