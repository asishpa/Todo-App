package in.asish.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.asish.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
