
package com.myChef.security;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Mocart
 */
@Service
public class TokenProvider {
 @Autowired
 private HazelcastInstance instance;
 
 public void put(String token, AuthUser auth ){
     instance.getMap("token-cache").put(token, auth);
 }

 public AuthUser get(String token){
     AuthUser res = null;

     if(token!=null){
       res = (AuthUser)instance.getMap("token-cache").get(token);
     }

     return res;
 }

 public String newToken(){
     return UUID.randomUUID().toString();
 }
}
