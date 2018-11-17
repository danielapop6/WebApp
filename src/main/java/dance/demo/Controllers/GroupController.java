package dance.demo.Controllers;

import dance.demo.Entities.Group;
import dance.demo.Exceptions.ResourceNotFoundException;
import dance.demo.Repositories.DanceStyleRepo;
import dance.demo.Repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/danceschool/groups")
public class GroupController {
    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private DanceStyleRepo danceStyleRepo;

    @GetMapping("/all")
    public List<Group> getAll() {
        return groupRepo.findAll();
    }

    @PostMapping("/add")
    public Group create(@Valid @RequestBody Group group) {
        return groupRepo.save(group);

    }
}
