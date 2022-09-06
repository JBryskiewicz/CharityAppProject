package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        User findByUserName(String username);
        @Query(value = "select * from user left join user_roles ur on user.id = ur.user_id where roles_id = ?1", nativeQuery = true)
        List<User> findAllByRoleId(long id);
}
