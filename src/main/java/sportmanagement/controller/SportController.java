package sportmanagement.controller;

import org.springframework.web.bind.annotation.*;
import sportmanagement.entity.Sport;
import sportmanagement.repo.SportRepository;

import java.util.List;

@RestController
@RequestMapping("/api/sports")
public class SportController {

    private final SportRepository sportRepo;

    public SportController(SportRepository sportRepo) {
        this.sportRepo = sportRepo;
    }

    // GET /api/sports
    @GetMapping
    public List<Sport> getAll() {
        return sportRepo.findAll();
    }

    // POST /api/sports?name=Judo
    @PostMapping
    public Sport add(@RequestParam String name) {
        Sport sport = sportRepo.findByNameIgnoreCase(name)
                .orElseGet(() -> new Sport(name));
        return sportRepo.save(sport);
    }

    // DELETE /api/sports/{id}
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (!sportRepo.existsById(id)) return "Sport not found";
        sportRepo.deleteById(id);
        return "Deleted";
    }
}