package dance.demo.Repositories;

import dance.demo.Entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepo extends JpaRepository<Group, Integer> {
}
