package dance.demo.Entities;

import dance.demo.Enums.Level;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DanceStyle danceStyle;

    public Group() {
    }

    public Group(Level level, DanceStyle danceStyle) {
        this.level = level;
        this.danceStyle = danceStyle;
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
