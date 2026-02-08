package sportmanagement.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import sportmanagement.entity.Athlete;
import sportmanagement.entity.Sport;
import sportmanagement.repo.AthleteRepository;
import sportmanagement.repo.SportRepository;

import java.util.List;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    private final AthleteRepository athleteRepo;
    private final SportRepository sportRepo;

    public AthleteController(AthleteRepository athleteRepo, SportRepository sportRepo) {
        this.athleteRepo = athleteRepo;
        this.sportRepo = sportRepo;
    }

    // GET /api/athletes
    @GetMapping
    public List<Athlete> getAll() {
        return athleteRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    // GET /api/athletes/sorted/age
    @GetMapping("/sorted/age")
    public List<Athlete> sortedByAge() {
        return athleteRepo.findAll(Sort.by("age"));
    }

    // GET /api/athletes/sport/{sportName}
    @GetMapping("/sport/{sportName}")
    public List<Athlete> bySport(@PathVariable String sportName) {
        return athleteRepo.findBySport_NameIgnoreCase(sportName);
    }

    // GET /api/athletes/championship/this-year (18+)
    @GetMapping("/championship/this-year")
    public List<Athlete> thisYear() {
        return athleteRepo.findByAgeGreaterThanEqual(18);
    }

    // GET /api/athletes/championship/next-year (<18)
    @GetMapping("/championship/next-year")
    public List<Athlete> nextYear() {
        return athleteRepo.findByAgeLessThan(18);
    }

    // POST /api/athletes?name=...&age=...&ranking=...&sportId=...
    @PostMapping
    public Athlete add(@RequestParam String name,
                       @RequestParam int age,
                       @RequestParam int ranking,
                       @RequestParam Long sportId) {

        Sport sport = sportRepo.findById(sportId)
                .orElseThrow(() -> new RuntimeException("Sport not found"));

        Athlete a = new Athlete(name, age, sport, ranking);
        return athleteRepo.save(a);
    }

    // PUT /api/athletes/{id}?age=..&ranking=..&sportId=..
    @PutMapping("/{id}")
    public Athlete update(@PathVariable Long id,
                          @RequestParam(required = false) Integer age,
                          @RequestParam(required = false) Integer ranking,
                          @RequestParam(required = false) Long sportId) {

        Athlete a = athleteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Athlete not found"));

        if (age != null) a.setAge(age);
        if (ranking != null) a.setRanking(ranking);

        if (sportId != null) {
            Sport sport = sportRepo.findById(sportId)
                    .orElseThrow(() -> new RuntimeException("Sport not found"));
            a.setSport(sport);
        }

        return athleteRepo.save(a);
    }

    // DELETE /api/athletes/{id}
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (!athleteRepo.existsById(id)) return "Athlete not found";
        athleteRepo.deleteById(id);
        return "Deleted";
    }
}