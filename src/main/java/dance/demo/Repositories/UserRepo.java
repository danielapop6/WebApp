package dance.demo.Repositories;

import dance.demo.Entities.Group;
import dance.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User,String> {

}
