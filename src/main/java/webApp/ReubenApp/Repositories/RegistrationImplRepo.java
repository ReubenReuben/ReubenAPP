package webApp.ReubenApp.Repositories;

import webApp.ReubenApp.Dto.RegistrationDTO;
import webApp.ReubenApp.Dto.UserDto;

public interface RegistrationImplRepo {
    boolean hasUserWithName(String name);
    UserDto findLoggedInUser(UserDto userDto);

//    UserDto createUser(RegistrationDTO registrationDTO);
//    boolean hasUserWithName(String name);
}
