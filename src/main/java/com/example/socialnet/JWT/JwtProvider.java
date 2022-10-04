package com.example.socialnet.JWT;


import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.security.*;

@Service
public class JwtProvider {
    private KeyStore keyStore;
    @PostConstruct
    public void init() throws Exception {

        try{
            keyStore=KeyStore.getInstance("JKS");
            InputStream resourceAsStream=getClass().getResourceAsStream("/yassirkey.jks");
            keyStore.load(resourceAsStream,"123456".toCharArray());
        }
        catch (Exception e){
            throw new Exception(e.getMessage());

        }
    }
    public String generateToken(Authentication authentication){
        User principal=(User) authentication.getPrincipal();
        return Jwts.builder().setSubject(principal.getUsername()).
                signWith(getPrivateKey()).compact();
    }
    public PrivateKey getPrivateKey(){
        try{
            return (PrivateKey) keyStore.getKey("yassirkey","123456".toCharArray());

        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
