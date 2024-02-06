package com.example.javaprojectspring_boot.group;

import com.example.javaprojectspring_boot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select g from Group as g
            """)
    List<Group> getAllGroups();

    @Query("""
            select u from User as u inner join Group as g on u.groupId=g.id
            """)
    List<User> getAllGroupWithUsers(Integer id);
}
