package dance.demo.Controllers;

import dance.demo.Entities.DanceStyle;
import dance.demo.Exceptions.ResourceNotFoundException;
import dance.demo.Repositories.DanceStyleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/danceschool/styles")
public class DanceStyleController {
    @Autowired
    private DanceStyleRepo danceStyleRepo;

    @GetMapping("/all")
    public List<DanceStyle> getAll() {
        return danceStyleRepo.findAll();
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<DanceStyle> getById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        DanceStyle danceStyle;
        try {
            danceStyle = danceStyleRepo.findOne(id);
            return ResponseEntity.ok().body(danceStyle);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Not found");
        }

    }

    @PostMapping("/add")
    public DanceStyle create(@Valid @RequestBody DanceStyle danceStyle) {
        return danceStyleRepo.save(danceStyle);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DanceStyle> update(@PathVariable(value = "id") String id,@Valid @RequestBody DanceStyle styleDetails) throws ResourceNotFoundException {

        DanceStyle danceStyle = null;
        try {
            danceStyleRepo.findOne(id);
            danceStyle.setName(styleDetails.getName());
            danceStyle.setDescription(styleDetails.getDescription());
            danceStyle.setOrigin(styleDetails.getOrigin());

            final DanceStyle updatedStyle = danceStyleRepo.save(danceStyle);
            return ResponseEntity.ok(updatedStyle);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Not found for this id :: " + id);
        }

    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        DanceStyle danceStyle;
        try {
            danceStyle = danceStyleRepo.findOne(id);
            danceStyleRepo.delete(danceStyle);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Not found for this id :: " + id);
        }

    }

}