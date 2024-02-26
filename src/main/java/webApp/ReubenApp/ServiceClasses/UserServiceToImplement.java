package webApp.ReubenApp.ServiceClasses;

import webApp.ReubenApp.Dto.RegistrationDTO;
import webApp.ReubenApp.Dto.UserDto;

public interface UserServiceToImplement {
    UserDto createUser(RegistrationDTO registrationDTO);
    boolean hasUserWithName(String name);
}
