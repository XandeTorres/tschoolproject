package tschool.tarasov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tschool.tarasov.dao.UserAuthDao;
import tschool.tarasov.models.users.Employee;


import java.util.Collections;


@Service
public class AuthenticationService implements UserDetailsService {

    private final UserAuthDao userAuthDao;

    @Autowired
    public AuthenticationService(UserAuthDao userAuthDao) {
        this.userAuthDao = userAuthDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee employee = userAuthDao.getUserbyUserName(s);

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" +employee.getRole());

        UserDetails userDetails = new User(employee.getEmail(),
                employee.getPassword(), Collections.singletonList(authority));

        return userDetails;
    }
}
