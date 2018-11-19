package dance.demo.Controllers;

import dance.demo.Entities.Group;
import dance.demo.Entities.User;
import dance.demo.Exceptions.ResourceNotFoundException;
import dance.demo.Repositories.GroupRepo;
import dance.demo.Repositories.UserRepo;
import dance.demo.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/danceschool/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GroupRepo groupRepo;

    @GetMapping("/all")
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") String username) throws ResourceNotFoundException {
        User user;
        try {
            user = userRepo.findOne(username);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Not found");
        }
    }

    @PostMapping("/addUserGroup/{groupId}")/////here
    public void addUserGroup( @PathVariable(value="groupId") Integer groupId, Principal principal) {
        String username = principal.getName();
        List<Group> groups = userRepo.getOne(username).getGroups();
        Group group = groupRepo.getOne(groupId);
        groups.add(group);
        userRepo.getOne(username).setGroups(groups);
    }


    boolean isPresent(String username) {
        User user = userRepo.findOne(username);
        return user != null;
    }

    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) {
        BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }



    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable(value = "id") String username,
                                       @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = null;
        try {
            userRepo.findOne(username);
            user.setPassword(userDetails.getPassword());
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());

            final User updatedUser = userRepo.save(user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Not found");
        }
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") String username)
            throws ResourceNotFoundException {
        User user = null;
        try {
            userRepo.findOne(username);
            userRepo.delete(user);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Not found");
        }
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal){
        String username = principal.getName();
        User user = userRepo.getOne(username);
        model.addAttribute("groups",user.getGroups());
        model.addAttribute("user",user);
        return "views/profile";
    }
}
