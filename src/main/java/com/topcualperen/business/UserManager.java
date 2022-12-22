package com.topcualperen.business;

import com.topcualperen.dataAccess.UserDao;
import com.topcualperen.dto.UserDto;
import com.topcualperen.entities.User;
import com.topcualperen.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManager implements UserService{

    private UserDao userDao;
    private ModelMapper modelMapper;

    @Autowired
    public UserManager(UserDao userDao, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto add(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(userDao.save(user), UserDto.class);
    }

    @Override
    public UserDto findByEmail(Long id) {
        Optional<User> user = userDao.findById(id);
        if(user.isPresent()){
            return modelMapper.map(user.get(), UserDto.class);
        } throw new UserNotFoundException("User Error");
    }

    @Override
    public UserDto deleteUser(Long id) {

//        User user = userDao.findById(id).orElseThrow(() -> new UserNotFoundException("Not Found User"));
//        return modelMapper.map(user.getEmail(), UserDto.class);
        Optional<User> user = userDao.findById(id);
        if(user.isPresent()){
            userDao.deleteById(id);
        }
            throw new UserNotFoundException("Not Delete !");
    }

    @Override
    public User getOneUserByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    public User saveOneUser(User newUser) {
        return userDao.save(newUser);
    }
}
