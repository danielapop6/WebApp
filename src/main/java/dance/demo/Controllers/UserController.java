package dance.demo.Controllers;

import dance.demo.Entities.User;
import dance.demo.Exceptions.ResourceNotFoundException;
import dance.demo.Repositories.UserRepo;
import dance.demo.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/danceschool/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

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

    public boolean isPresent(String username){
        User user=userRepo.findOne(username);
        if(user!=null){
            return true;
        }
        return false;
    }

    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) {
        user.setPassword(Utils.getMD5Hash(user.getPassword()));
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
}
