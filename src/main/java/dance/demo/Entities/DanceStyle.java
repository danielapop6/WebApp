package dance.demo.Entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dancestyles")
public class DanceStyle {

    @Id
    @Column(name = "name", length = 20, nullable = false, unique = true)
    private String name;

    @Column(name = "origin", length = 30, nullable = false)
    private String origin;

    @Column(name = "description", length = 100)
    private String description;

    @OneToMany(mappedBy = "danceStyle",cascade = CascadeType.MERGE)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Set<Group> groups = new HashSet<>();

    public DanceStyle() {
    }

    public DanceStyle(String name, String origin, String description) {
        this.name = name;
        this.origin = origin;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "DanceStyle{" +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
