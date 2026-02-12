package sportmanagement.controller;

import org.springframework.web.bind.annotation.*;
import sportmanagement.entity.Athlete;
import sportmanagement.pool.AthletePool;
import sportmanagement.repo.AthleteRepository;

import java.util.List;

@RestController
@RequestMapping("/api/pool")
public class PoolController {

    private final AthleteRepository athleteRepo;
    private final AthletePool pool;

    public PoolController(AthleteRepository athleteRepo, AthletePool pool) {
        this.athleteRepo = athleteRepo;
        this.pool = pool;
    }

    @PostMapping("/reload")
    public String reload() {
        List<Athlete> all = athleteRepo.findAll();
        pool.loadAll(all);
        return "Pool loaded: " + all.size();
    }

    @GetMapping("/all")
    public List<Athlete> all() {
        return pool.getAll();
    }

    @GetMapping("/search")
    public List<Athlete> search(@RequestParam String q) {
        return pool.findByNameContains(q);
    }

    @GetMapping("/filter/age")
    public List<Athlete> filterAge(@RequestParam int min) {
        return pool.filterAgeAtLeast(min);
    }

    @GetMapping("/sort/rank")
    public List<Athlete> sortRank() {
        return pool.sortByRankAsc();
    }

    @GetMapping("/sort/age")
    public List<Athlete> sortAge() {
        return pool.sortByAgeDesc();
    }
}