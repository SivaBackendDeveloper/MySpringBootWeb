package com.project.MyWebApp;

import com.project.MyWebApp.user.User;
import com.project.MyWebApp.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)

public class UserRepositoryTests {

    @Autowired private UserRepository repo;


// Test Case For Add New User
   @Test
    public void testAddNew(){
       User user = new User();
       user.setEmail("ravi.kumar@gmail.com");
       user.setPassword("ravi12345");
       user.setFirstName("Ravi");
       user.setLastName("Ch");

       User savedUser = repo.save(user);

       Assertions.assertNotNull(savedUser);
       Assertions.assertNotEquals(savedUser.getId(),0);
   }

   // Test Case for List of all Users
   @Test
   public void testListAll(){
      Iterable<User> users = repo.findAll();
      Assertions.assertNotEquals(users,0);

      for (User user :users){
         System.out.println(user);
      }
   }

   // Test Case For Update the user
   @Test
   public void testUpdate(){
      Integer userId =2;
      Optional<User> optionalUser = repo.findById(userId);
      User user = optionalUser.get();
      user.setPassword("hello1234");
      repo.save(user);

      User updateUser = repo.findById(userId).get();
      Assertions.assertEquals(updateUser.getPassword(),"hello1234");
   }

   //Test case for Get the user details by Id
   @Test
   public void testGet(){
      Integer userId =2;
      Optional<User> optionalUser = repo.findById(userId);

      Assertions.assertEquals(true, optionalUser.isPresent());
      System.out.println(optionalUser.get());
   }


   //Test case for Delete the user
   @Test
   public void testDelete(){
      Integer userId =2;
      repo.deleteById(userId);

      Optional<User> optionalUser = repo.findById(userId);
      Assertions.assertFalse(optionalUser.isPresent());
   }
}
