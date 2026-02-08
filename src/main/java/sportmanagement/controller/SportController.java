package sportmanagement.controller;

import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sportmanagement.entity.Sport;
import sportmanagement.repo.AthleteRepository;
import sportmanagement.repo.SportRepository;

import java.util.List;

@RestController
@RequestMapping("/api/sports")
public class SportController {

    private final SportRepository sportRepo;
    private final AthleteRepository athleteRepo;

    public SportController(SportRepository sportRepo, AthleteRepository athleteRepo) {
        this.sportRepo = sportRepo;
        this.athleteRepo = athleteRepo;
    }

    // GET /api/sports
    @GetMapping
    public List<Sport> getAll() {
        return sportRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    // POST /api/sports?name=Judo
    @PostMapping
    public Sport add(@RequestParam String name) {
        Sport sport = sportRepo.findByNameIgnoreCase(name)
                .orElseGet(() -> new Sport(name));
        return sportRepo.save(sport);
    }

    // DELETE
    @Transactional
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (!sportRepo.existsById(id)) return "Sport not found";

        athleteRepo.deleteBySport_Id(id);
        sportRepo.deleteById(id);

        return "Deleted sport and related athletes";
    }
}