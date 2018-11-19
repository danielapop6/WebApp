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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
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

    @GetMapping("/addUserGroup")
    public String addUserGroup(Integer groupId, Principal principal, Model model) {
        String username = principal.getName();
        addUG(groupId,username);
        return "index";
    }
    private void addUG(Integer groupId, String username){

        Group group = groupRepo.getOne(groupId);
        User user = userRepo.getOne(username);

        user.getGroups().add(group);
        group.getUsers().add(user);
        userRepo.save(user);
        groupRepo.save(group);
    }

    @GetMapping("/deleteUserGroup")
    public String deleteUserGroup(Integer groupId, Principal principal, Model model) {
        String username = principal.getName();
        deleteUG(groupId,username);
        return "index";
    }

    private void deleteUG(Integer groupId, String username){

        Group group = groupRepo.getOne(groupId);
        User user = userRepo.getOne(username);

        user.getGroups().remove(groupId);
        group.getUsers().remove(username);
        userRepo.save(user);
        groupRepo.save(group);
    }


    boolean isPresent(String username) {
        User user = userRepo.findOne(username);
        return user != null;
    }

    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        List<Group> groups = new ArrayList<>();
        user.setGroups(groups);
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



    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepo.getOne(username);
        model.addAttribute("groups", user.getGroups());
        model.addAttribute("user", user);
        return "views/profile";
    }


}
