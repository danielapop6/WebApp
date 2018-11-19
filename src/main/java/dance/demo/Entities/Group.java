package dance.demo.Entities;

import dance.demo.Enums.Level;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "group_id", length = 20, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "level", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private DanceStyle danceStyle;

    @ManyToMany(mappedBy = "groups")
    private List<User> users;

    public Group() {
    }

    public Group(Level level, DanceStyle danceStyle, List<User> users) {
        this.level = level;
        this.danceStyle = danceStyle;
        this.users = users;
    }


    public Group(Level level, DanceStyle danceStyle) {
        this.level = level;
        this.danceStyle = danceStyle;
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public DanceStyle getDanceStyle() {
        return danceStyle;
    }

    public void setDanceStyle(DanceStyle danceStyle) {
        this.danceStyle = danceStyle;
    }
}
