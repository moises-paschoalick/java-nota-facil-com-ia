package com.gotocode.nota.repository;

import com.gotocode.nota.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber")
    User findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}
