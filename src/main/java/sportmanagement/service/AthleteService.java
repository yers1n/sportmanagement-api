package sportmanagement.service;

import sportmanagement.entity.Athlete;

import java.util.List;

public interface AthleteService {
    Athlete getById(Long id);

    List<Athlete> getAllSortedById();
    List<Athlete> sortedByAge();
    List<Athlete> bySport(String sportName);
    List<Athlete> thisYear();
    List<Athlete> nextYear();
    long eligibleCount();

    Athlete create(String name, int age, int ranking, Long sportId);
    Athlete createJson(String name, int age, int rank, Long sportId);
    Athlete update(Long id, Integer age, Integer ranking, Long sportId);
    void delete(Long id);
}