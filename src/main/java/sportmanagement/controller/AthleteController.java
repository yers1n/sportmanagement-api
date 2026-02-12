package sportmanagement.controller;

import org.springframework.web.bind.annotation.*;
import sportmanagement.dto.AthleteCreateRequest;
import sportmanagement.entity.Athlete;
import sportmanagement.service.AthleteService;

import java.util.List;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping
    public List<Athlete> getAll() {
        return athleteService.getAllSortedById();
    }

    @GetMapping("/sorted/age")
    public List<Athlete> sortedByAge() {
        return athleteService.sortedByAge();
    }

    @GetMapping("/sport/{sportName}")
    public List<Athlete> bySport(@PathVariable String sportName) {
        return athleteService.bySport(sportName);
    }

    @GetMapping("/championship/this-year")
    public List<Athlete> thisYear() {
        return athleteService.thisYear();
    }

    @GetMapping("/championship/next-year")
    public List<Athlete> nextYear() {
        return athleteService.nextYear();
    }

    @GetMapping("/eligible/count")
    public long eligibleCount() {
        return athleteService.eligibleCount();
    }

    @GetMapping("/{id}/eligibility-label")
    public String eligibilityLabel(@PathVariable Long id) {
        return athleteService.getById(id).eligibilityLabel();
    }

    @PostMapping
    public Athlete add(@RequestParam String name,
                       @RequestParam int age,
                       @RequestParam int ranking,
                       @RequestParam Long sportId) {
        return athleteService.create(name, age, ranking, sportId);
    }

    @PostMapping("/json")
    public Athlete addJson(@RequestBody AthleteCreateRequest req) {
        return athleteService.createJson(req.name, req.age, req.rank, req.sportId);
    }

    @PutMapping("/{id}")
    public Athlete update(@PathVariable Long id,
                          @RequestParam(required = false) Integer age,
                          @RequestParam(required = false) Integer ranking,
                          @RequestParam(required = false) Long sportId) {
        return athleteService.update(id, age, ranking, sportId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        athleteService.delete(id);
        return "Deleted";
    }
}