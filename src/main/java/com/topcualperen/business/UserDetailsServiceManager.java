package com.topcualperen.business;

import com.topcualperen.dataAccess.UserDao;
import com.topcualperen.entities.User;
import com.topcualperen.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceManager implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserDetailsServiceManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userDao.findById(id).get();
        return JwtUserDetails.create(user); // user -> userDetails olarak döndürüyoruz.
    }

}
