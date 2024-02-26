package webApp.ReubenApp.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import webApp.ReubenApp.Entities.User;
import webApp.ReubenApp.Repositories.UserRepo;

import java.util.ArrayList;
import java.util.Optional;
@Service
public class UserServiceImplJwt implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepo.findByName(name);
        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return new org.springframework.security.core.userdetails.User(
                userOptional.get().getName(),
                userOptional.get().getPassword(),
                new ArrayList<>()
        );
    }
}
