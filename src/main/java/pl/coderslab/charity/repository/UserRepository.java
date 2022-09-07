package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.domain.Role;
import pl.coderslab.charity.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        User findByUserName(String username);
        @Query("select u from User u where ?1 member of u.roles")
        List<User> findAllByRole(Role role);
}
