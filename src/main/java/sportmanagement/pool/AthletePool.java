package sportmanagement.pool;

import org.springframework.stereotype.Component;
import sportmanagement.entity.Athlete;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class AthletePool {

    private final Map<Long, Athlete> byId = new HashMap<>();

    public void put(Athlete athlete) {
        if (athlete == null || athlete.getId() == null) return;
        byId.put(athlete.getId(), athlete);
    }

    public void loadAll(List<Athlete> athletes) {
        byId.clear();
        for (Athlete a : athletes) {
            put(a);
        }
    }

    public List<Athlete> getAll() {
        return new ArrayList<>(byId.values());
    }

    public Optional<Athlete> findById(Long id) {
        return Optional.ofNullable(byId.get(id));
    }

    public List<Athlete> findByNameContains(String part) {
        String p = (part == null) ? "" : part.toLowerCase();
        return byId.values().stream()
                .filter(a -> a.getName() != null && a.getName().toLowerCase().contains(p))
                .collect(Collectors.toList());
    }

    public List<Athlete> filterAgeAtLeast(int minAge) {
        return byId.values().stream()
                .filter(a -> a.getAge() >= minAge)
                .collect(Collectors.toList());
    }

    public List<Athlete> sortByRankAsc() {
        return byId.values().stream()
                .sorted(Comparator.comparingInt(Athlete::getRank))
                .collect(Collectors.toList());
    }

    public List<Athlete> sortByAgeDesc() {
        return byId.values().stream()
                .sorted(Comparator.comparingInt(Athlete::getAge).reversed())
                .collect(Collectors.toList());
    }
}