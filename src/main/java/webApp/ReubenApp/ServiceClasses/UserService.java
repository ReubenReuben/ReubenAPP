package webApp.ReubenApp.ServiceClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import webApp.ReubenApp.Dto.RegistrationDTO;
import webApp.ReubenApp.Dto.UserDto;
import webApp.ReubenApp.Entities.User;
import webApp.ReubenApp.Repositories.UserRepo;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserServiceToImplement {
    @Autowired
    private UserRepo userRepo;

//    @Override
//    public UserDetails loadUserByName(String name) throws UsernameNotFoundException {
//        Optional<User> user = userRepo.findByName(name);
//        if(user== null){
//            throw new UsernameNotFoundException("user not found");
//        }
//        return new UserPrinciples(user);
//    }

//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//
//        Optional<User> userOptional = userRepo.findByName(name);
//        if(userOptional.isEmpty())
//            throw new UsernameNotFoundException("User not found");
//        return new org.springframework.security.core.userdetails.User(
//                userOptional.get().getName(),
//                userOptional.get().getPassword(),
//                new ArrayList<>()
//        );
//    }
    public Optional<User> getCurrentUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByName(name);
    }

    @Override
    public UserDto createUser(RegistrationDTO registrationDTO) {
       User user = new User();

       user.setName(registrationDTO.getName());
       user.setPassword(new BCryptPasswordEncoder().encode(registrationDTO.getPassword()));

       User createdUser= userRepo.save(user);
       UserDto createdUserDto= new UserDto();
       createdUserDto.setId(createdUser.getId());

       return createdUserDto;
    }

    @Override
    public boolean hasUserWithName(String name) {
        return userRepo.findByName(name).isPresent();
    }
}
