package com.hm.user.service.Repositories;

import com.hm.user.service.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
