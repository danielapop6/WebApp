package dance.demo.Repositories;

import dance.demo.Entities.Group;
import dance.demo.Enums.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepo extends JpaRepository<Group,Integer> {
}
