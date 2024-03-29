package com.backend.lib.Service.Implementation;

import com.backend.lib.Entity.User;
import com.backend.lib.Service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    public String generateToken(UserDetails userDetails) {
        //implemented by User Entity and overidden the methods and returned the required email as username
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *24 ))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        //pass the extraClaims from the args and pass to setClaims it gets the req info of the token
        //then the token expiration time will be increased
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +1000 * 60*60*24*7))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserName(String token){
        //here this method will return the username by using extractClaim method which uses token
        return extractClaim(token, Claims::getSubject);
//        Claims::getSubject is a method reference to the getSubject method in the Claims class, which is used to retrieve the subject claim from the JWT return the email.
    }


    //extract username form token and that is extract claims
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        //here new method which extract all claims(userDetails) from the token
        final Claims claims = extractAllClaims(token);
        //here we are using this method to get the particular claim
        return claimsResolvers.apply(claims);
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode("dad9259c284118ac891084a07711a9c21ca2bc02ab3b6aa941041c841d7ecd64");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        //jwts will set the signkey by getting the secret key from getSigin method and then it parse the token and get the body which contains all the claims of token
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }

    //for validating a token
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        //to validate email and token we return boolean where use extractUsername() to
        //extract email from token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        //return boolean of username matches with api called email and also boolean of token expired or not
    }



    private boolean isTokenExpired(String token) {
        //for validation we need to check whether token is expired or not by using claims we get all the info of token and using getExpiration we get the time
        //and this date should be before current time if not its expired
        return extractClaim(token,Claims::getExpiration).before(new Date());

    }


}
