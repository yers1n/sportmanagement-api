package sportmanagement.pool;

import org.springframework.stereotype.Component;
import sportmanagement.entity.Athlete;

import java.util.Comparator;
import java.util.List;

@Component
public class DataPoolDemo {

    private final DataPool<Athlete> pool = new DataPool<>();

    public void load(List<Athlete> athletes) {
        athletes.forEach(a -> pool.put(a.getId(), a));
    }

    public List<Athlete> findAdults() {
        return pool.filter(a -> a.getAge() >= 18);   // Lambda
    }

    public List<Athlete> sortByRank() {
        return pool.sort(Comparator.comparingInt(Athlete::getRank)); // Lambda + method reference
    }
}