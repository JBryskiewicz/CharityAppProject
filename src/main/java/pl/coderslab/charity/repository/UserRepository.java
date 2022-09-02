package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        User findByUserName(String username);
}
