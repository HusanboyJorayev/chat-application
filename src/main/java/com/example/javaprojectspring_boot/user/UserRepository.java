package com.example.javaprojectspring_boot.user;

import com.example.javaprojectspring_boot.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select  true from User as u where u.phoneNumber=:n
            """)
    boolean findByPhone(@Param("n") String number);

    @Query("""
            select u from User as u
            """)
    List<User> getAllUsers();

}
