package sportmanagement.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sportmanagement.entity.Athlete;
import sportmanagement.entity.Sport;
import sportmanagement.exception.NotFoundException;
import sportmanagement.exception.ValidationException;
import sportmanagement.repo.AthleteRepository;
import sportmanagement.repo.SportRepository;
import sportmanagement.service.AthleteService;
import sportmanagement.service.EligibilityService;

import java.util.List;

@Service
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepo;
    private final SportRepository sportRepo;
    private final EligibilityService eligibilityService;

    public AthleteServiceImpl(AthleteRepository athleteRepo,
                              SportRepository sportRepo,
                              EligibilityService eligibilityService) {
        this.athleteRepo = athleteRepo;
        this.sportRepo = sportRepo;
        this.eligibilityService = eligibilityService;
    }

    @Override
    public Athlete getById(Long id) {
        return athleteRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Athlete not found"));
    }

    @Override
    public List<Athlete> getAllSortedById() {
        return athleteRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Athlete> sortedByAge() {
        return athleteRepo.findAll(Sort.by("age"));
    }

    @Override
    public List<Athlete> bySport(String sportName) {
        return athleteRepo.findBySport_NameIgnoreCase(sportName);
    }

    @Override
    public List<Athlete> thisYear() {
        return athleteRepo.findByAgeGreaterThanEqual(18);
    }

    @Override
    public List<Athlete> nextYear() {
        return athleteRepo.findByAgeLessThan(18);
    }

    @Override
    public long eligibleCount() {
        return eligibilityService.countEligible(athleteRepo.findAll());
    }

    private void validate(String name, int age, int rank) {
        if (name == null || name.trim().isEmpty()) throw new ValidationException("Name is required");
        if (age < 0) throw new ValidationException("Age must be >= 0");
        if (rank < 0) throw new ValidationException("Ranking must be >= 0");
    }

    private Sport getSportOrThrow(Long sportId) {
        return sportRepo.findById(sportId)
                .orElseThrow(() -> new NotFoundException("Sport not found"));
    }

    @Override
    public Athlete create(String name, int age, int ranking, Long sportId) {
        validate(name, age, ranking);
        Sport sport = getSportOrThrow(sportId);
        return athleteRepo.save(
                new sportmanagement.pattern.builder.AthleteBuilder()
                        .name(name.trim())
                        .age(age)
                        .rank(ranking)
                        .sport(sport)
                        .build()
        );
    }

    @Override
    public Athlete createJson(String name, int age, int rank, Long sportId) {
        return create(name, age, rank, sportId);
    }

    @Override
    public Athlete update(Long id, Integer age, Integer ranking, Long sportId) {
        Athlete a = getById(id);

        if (age != null) {
            if (age < 0) throw new ValidationException("Age must be >= 0");
            a.setAge(age);
        }
        if (ranking != null) {
            if (ranking < 0) throw new ValidationException("Ranking must be >= 0");
            a.setRank(ranking);
        }
        if (sportId != null) {
            a.setSport(getSportOrThrow(sportId));
        }
        return athleteRepo.save(a);
    }

    @Override
    public void delete(Long id) {
        if (!athleteRepo.existsById(id)) throw new NotFoundException("Athlete not found");
        athleteRepo.deleteById(id);
    }
}