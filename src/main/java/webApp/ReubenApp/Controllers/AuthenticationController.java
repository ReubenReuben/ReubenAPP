package webApp.ReubenApp.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import webApp.ReubenApp.Dto.AuthenticationDto;
import webApp.ReubenApp.Entities.User;
import webApp.ReubenApp.Repositories.UserRepo;
import webApp.ReubenApp.jwt.JwtUtils;

import java.io.IOException;
import java.util.Optional;

public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepo userRepo;
    public  static  final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING= "Authorization";
    @PostMapping("/authentication")
    public void createAuthenticationToken(@RequestBody AuthenticationDto authenticationDto,
                                          HttpServletResponse response) throws IOException, JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDto.getName(), authenticationDto.getPassword()));

        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect name or Password");
        }catch (DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "user is not created");
            return;
        }
        final UserDetails userDetails= userDetailsService.loadUserByUsername(authenticationDto.getName());

        Optional<User> optionalUser= userRepo.findByName(userDetails.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails.getUsername());

/////////////////////////// to make it give me user id instead of token

        if(optionalUser.isPresent()){
            response.getWriter().write(new JSONObject()
                    .put("userId", optionalUser.get().getId())
                    .toString()
            );

        }
        response.addHeader("Access-Control-Expose-Header", "Authorization");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, X-Requested-With, Content-Type, Accept, X-Custom-Header");
        response.setHeader(HEADER_STRING, TOKEN_PREFIX + jwt);



    }

}
