package sportmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "athlete")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int age;
    private int ranking;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sport_id")
    private Sport sport;

    public Athlete() {}

    public Athlete(String name, int age, Sport sport, int ranking) {
        this.name = name;
        this.age = age;
        this.sport = sport;
        this.ranking = ranking;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public int getAge() { return age; }

    public int getRanking() { return ranking; }

    public Sport getSport() { return sport; }

    public void setName(String name) { this.name = name; }

    public void setAge(int age) { this.age = age; }

    public void setRanking(int ranking) { this.ranking = ranking; }

    public void setSport(Sport sport) { this.sport = sport; }
}