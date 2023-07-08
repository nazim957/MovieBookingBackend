package com.moviebooking.user.repo;

import com.moviebooking.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserName(String userName);

    public User findByEmail(String email);



}
