package dance.demo.Controllers;

import dance.demo.Entities.Group;
import dance.demo.Exceptions.ResourceNotFoundException;
import dance.demo.Repositories.DanceStyleRepo;
import dance.demo.Repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GroupController {
    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private DanceStyleRepo danceStyleRepo;

    @GetMapping("/allGroups")
    public List<Group> getAll() {
        return groupRepo.findAll();
    }

    @GetMapping("/groups")
    public String getAllInModel(Model model) {
        model.addAttribute("myGroups", groupRepo.findAll());
        return "views/groupsList";
    }

    @PostMapping("/addGroup")
    public Group create(@Valid @RequestBody Group group) {
        return groupRepo.save(group);

    }
}
