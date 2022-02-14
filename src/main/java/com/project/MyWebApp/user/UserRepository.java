package com.project.MyWebApp.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    // method is for delete user based on their id
    public Long countById(Integer id);

}
