package dance.demo.Repositories;

import dance.demo.Entities.DanceStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanceStyleRepo extends JpaRepository<DanceStyle, String> {
    List<DanceStyle> findByNameLike(String name);
}
