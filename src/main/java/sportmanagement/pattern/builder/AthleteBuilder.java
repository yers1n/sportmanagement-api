package sportmanagement.pattern.builder;

import sportmanagement.entity.Athlete;
import sportmanagement.entity.Sport;

public class AthleteBuilder {

    private String name;
    private int age;
    private int rank;
    private Sport sport;

    public AthleteBuilder name(String name) {
        this.name = name;
        return this;
    }

    public AthleteBuilder age(int age) {
        this.age = age;
        return this;
    }

    public AthleteBuilder rank(int rank) {
        this.rank = rank;
        return this;
    }

    public AthleteBuilder sport(Sport sport) {
        this.sport = sport;
        return this;
    }

    public Athlete build() {
        return new Athlete(name, age, sport, rank);
    }
}