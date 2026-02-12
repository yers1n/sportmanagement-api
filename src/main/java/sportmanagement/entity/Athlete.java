package sportmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "athlete")
public class Athlete extends Person implements Eligible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rank;

    @ManyToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;

    public Athlete() {}

    public Athlete(String name, int age, Sport sport, int rank) {
        setName(name);
        setAge(age);
        this.sport = sport;
        this.rank = rank;
    }

    public Long getId() { return id; }

    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }

    public Sport getSport() { return sport; }
    public void setSport(Sport sport) { this.sport = sport; }

    @Override
    public boolean isEligible() {
        return getAge() >= 18;
    }
}