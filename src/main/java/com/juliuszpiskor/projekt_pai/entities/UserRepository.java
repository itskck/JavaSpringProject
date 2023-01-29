package com.juliuszpiskor.projekt_pai.entities;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    
    public User findUserById(int id);

    public int countByLogin(String login);
    
    public User findUserByLogin(String login);
    
    public void deleteByLogin(String login);
    

}