package com.topcualperen.api.controllers;

import com.topcualperen.business.UserService;
import com.topcualperen.dto.UserDto;
import com.topcualperen.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> add(@RequestBody UserDto user){
        UserDto resultUser = userService.add(user);
        return ResponseEntity.ok(resultUser);
    }

    @GetMapping("/findByEmail/{id}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable("id") Long id){
        UserDto userDtoFind = userService.findByEmail(id);
        return ResponseEntity.ok(userDtoFind);
    }

    @DeleteMapping("/deleteEmail/{id}")
    public ResponseEntity deleteEmail(@PathVariable("id") Long id){
//        try{
//            UserDto userDtoDelete = userService.deleteUser(id);
//        }catch (Exception e){
//
//        }
//        return userService.deleteUser(id);
        UserDto userDtoDelete = userService.deleteUser(id);
        return ResponseEntity.ok(userDtoDelete);

    }

    @ExceptionHandler({UserNotFoundException.class})
    public String UserNotFoundId(){
        return "Not Found !";
    }


}
