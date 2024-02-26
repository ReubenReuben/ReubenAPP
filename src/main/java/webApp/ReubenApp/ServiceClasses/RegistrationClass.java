package webApp.ReubenApp.ServiceClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import webApp.ReubenApp.Dto.RegistrationDTO;
import webApp.ReubenApp.Dto.UserDto;
import webApp.ReubenApp.Entities.User;
import webApp.ReubenApp.Repositories.RegistrationImplRepo;
import webApp.ReubenApp.Repositories.UserRepo;

@Service
public class RegistrationClass implements RegistrationImplRepo {
//    private final InMemoryUserDetailsManager userDetailsManager;

//    public RegistrationClass(InMemoryUserDetailsManager userDetailsManager) {
//        this.userDetailsManager = userDetailsManager;
//    }
    @Autowired
    private UserRepo userRepo;
    public UserDto createUser(RegistrationDTO registrationDTO){
        User user = new User();
        user.setName(registrationDTO.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(registrationDTO.getPassword()));
        User createUser = userRepo.save(user);
        UserDto createUserDto = new UserDto();
        createUserDto.setId(createUser.getId());
        return  createUserDto;

    }

    @Override
    public boolean hasUserWithName(String name) {
        return userRepo.findByName(name).isPresent();
    }

    //track user
    @Override
    public UserDto findLoggedInUser(UserDto userDto) {
       User user= new User();
return null;
    }
}
