package webApp.ReubenApp.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;




@Component
public class JwtUtils {
    private static final String SECRET_KEY="ejUVefEVPnyfHCyGW7ycbiU5iPeB4yqJCRWmZ/HT/jOUDMufgF6tRmV7D3EAIsle";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }




    //extract all claims
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final  Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    //token for no extra claims




    //token generator for extra claims
    public String createToken(Map<String, Object> claims, String userName){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userName)//user from class
                .setIssuedAt(new Date(System.currentTimeMillis()))   //set date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 22))
                .signWith(getsignInKey(), SignatureAlgorithm.HS256)  // call getsignInKey that you created
                .compact(); // will return the whole claim

    }
    //    private String createToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
//                .signWith(SignatureAlgorithm.HS256, getsignInKey())
//                .compact();
//    }
    // Generate Token
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, userDetails.getUsername());
//    }
    //token checker
    //check if token = userdetails
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()))
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    //main method
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getsignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private Key getsignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

