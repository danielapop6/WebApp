package dance.demo.Controllers;

import dance.demo.Entities.DanceStyle;
import dance.demo.Exceptions.ResourceNotFoundException;
import dance.demo.Repositories.DanceStyleRepo;
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
public class DanceStyleController {
    @Autowired
    private DanceStyleRepo danceStyleRepo;

    @GetMapping("/all")
    public List<DanceStyle> getAll() {
        return danceStyleRepo.findAll();
    }

    @GetMapping("/allInModel")
    public String getAllInModel(Model model,@RequestParam(defaultValue = "") String name) {
        model.addAttribute("myStyles",danceStyleRepo.findByNameLike("%"+name+"%"));
        return "views/styleList";
    }


    @GetMapping("/allStyles/{id}")
    public ResponseEntity<DanceStyle> getById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        DanceStyle danceStyle;
        try {
            danceStyle = danceStyleRepo.findOne(id);
            return ResponseEntity.ok().body(danceStyle);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Not found");
        }

    }

    @PostMapping("/addStyle")
    public DanceStyle create(@Valid @RequestBody DanceStyle danceStyle) {
        return danceStyleRepo.save(danceStyle);
    }

    @PutMapping("/updateStyle/{id}")
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

    @DeleteMapping("/deleteStyle/{id}")
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
