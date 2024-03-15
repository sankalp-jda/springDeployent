package org.example.Controller;

import org.bson.types.ObjectId;
import org.example.Model.User;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*",allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/registration")
    public User register(@RequestBody User user){
        User res=userService.register(user);
        return res;

    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();
        return userService.userLogin(userEmail, userPassword);
    }

    @GetMapping("/findAll")
    public List<User> findAll(){

        return userService.findAll();
    }

    @PutMapping("/update")
    public User update(@RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id){
        userService.delete(id);
    }





}
