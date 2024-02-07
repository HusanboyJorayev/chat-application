package com.example.javaprojectspring_boot.user;

import com.example.javaprojectspring_boot.chat.Chat;
import com.example.javaprojectspring_boot.group.Group;
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


    @Query("""
            select u from User as u inner join Contact as c on c.userId=:id
            """)
    List<User> deleteContact(@Param(value = "id") Integer userId);

    @Query("""
            select u from User as u inner join Group as g on g.userId=:id
            """)
    List<User> deleteGroup(@Param(value = "id") Integer userId);


    @Query("""
                     select  u from User as u inner join Contact as c on c.userId=:id
            """)
    List<User> addUser(Integer id);

}
