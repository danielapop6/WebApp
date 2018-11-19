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

        DanceStyle style = new DanceStyle("Salsa","Sometimes in life confusion tends to arise and only dialogue of dance seems to make sense.","Spain");
        DanceStyle style2 = new DanceStyle("Bachata","Hand in hand, on the edge of the sand, they danced by the light of the moon.","Caribbean Spanish");
        DanceStyle style3 = new DanceStyle("Zouk","Nobody cares if you can’t dance well. Just get up and dance. Great dancers are great because of their passion.","Guadeloupian Creole");
        DanceStyle style4 = new DanceStyle("Kizomba","The job of feets is walking, but their hobby is dancing.","Angola");
        DanceStyle style5 = new DanceStyle("Waltz","Dancing is a perpendicular expression of a horizontal desire.","Germany");
        DanceStyle style6 = new DanceStyle("Cha cha","Those who were seen dancing were thought to be insane by those who could not hear the music.","Latin American Spanish");

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
