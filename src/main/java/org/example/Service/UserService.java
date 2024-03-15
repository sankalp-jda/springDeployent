package org.example.Service;

import org.bson.types.ObjectId;
import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public User register(User user){
        User saveall=userRepository.save(user);
        System.out.println("register successful");
        return saveall;
    }

    public Boolean existByEmail(String email){
        Optional<User> usersObj = Optional.ofNullable(userRepository.getUserByUserName(email));
        if(!usersObj.isEmpty()){
            return true;
        }
        return false;
    }

    public void delete(String id){
        userRepository.deleteById(new ObjectId(id));
    }

    public User update(User user){

            return userRepository.save(user);
    }


    public String userLogin(String email, String password ) {
        boolean foundUsers = existByEmail(email);
        if(foundUsers) {
            User user = userRepository.getUserByUserName(email);
            if(user.getPassword().equals(password)) {
                return "{" +
                        "\"message\":"+"Successfully logged in\",\n"+
                        "\"data\":"+user+",\n"+
                        "\"Email:"+user.getEmail()+"\n"+"\token:"+tokenService.createTokenFunction(user.getId())
                        +"\n"+
                        "}";
            }
        }
        return "{" +
                "\"message\":"+"Authentication Failed\",\n"+
                "}";
    }
    public List<User>findAll(){
        return userRepository.findAll();
    }

    public User findById(ObjectId id){

        Optional<User>optionalProduct = userRepository.findById(id);
        if(optionalProduct.isEmpty())return null;
        return  optionalProduct.get();
    }
//    public boolean existsById(Object id){
//        Optional<User> tempObj = userRepository.findById(id);
//        // Optional State for Null pointer exception
//        if(tempObj.isEmpty()){
//            return false;
//        }
//
//        return true;
//    }
}
