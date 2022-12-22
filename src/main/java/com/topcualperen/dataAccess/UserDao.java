package com.topcualperen.dataAccess;

import com.topcualperen.dto.UserDto;
import com.topcualperen.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(UserDto email);

    User findByUserName(String userName);
}
