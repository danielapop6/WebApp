package dance.demo;

import dance.demo.Controllers.DanceStyleController;
import dance.demo.Controllers.GroupController;
import dance.demo.Controllers.UserController;
import dance.demo.Entities.DanceStyle;
import dance.demo.Entities.Group;
import dance.demo.Entities.User;
import dance.demo.Enums.Level;
import dance.demo.Exceptions.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserController userController;
    @Autowired
    private DanceStyleController danceStyleController;
    @Autowired
    private GroupController groupController;

    @Before
    public void initDB() {
        User user = new User("daniela", "daniela", "Pop", "Daniela", true);
        userController.create(user);
        User user2 = new User("lavinia", "lavinia", "Lau", "Lavinia", false);
        userController.create(user2);

        DanceStyle style = new DanceStyle("salsa", "Spain", "Funny");
        DanceStyle style2 = new DanceStyle("bachata", "Dominican Republik", "Romantic");
        DanceStyle style3 = new DanceStyle("kizomba", "Spain", "Romantic");
        DanceStyle style4 = new DanceStyle("zouk", "Caraibee", "Romantic");

        danceStyleController.create(style);
        danceStyleController.create(style2);
        danceStyleController.create(style3);
        danceStyleController.create(style4);

        Group group = new Group(Level.BEGINNER, style);
        Group group2 = new Group(Level.INTERMEDIATE, style2);

        groupController.create(group);
        groupController.create(group2);

    }
    @Test
    public void testUser() {
        try {
            ResponseEntity<User> user = userController.getById("daniela");
            assertNotNull(user);
            assertEquals(user.getBody().getUsername(), "daniela");
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStyle() {
        try {
            ResponseEntity<DanceStyle> style = danceStyleController.getById("salsa");
            assertNotNull(style);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGroup() {
        List<Group> group = groupController.getAll();
        assertNotNull(group);
        assertNotEquals(group.size(), 0);
    }

}
