package sportmanagement.controller;

import org.springframework.web.bind.annotation.*;
import sportmanagement.dto.SportCreateRequest;
import sportmanagement.entity.Sport;
import sportmanagement.service.SportService;

import java.util.List;

@RestController
@RequestMapping("/api/sports")
public class SportController {

    private final SportService sportService;

    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping
    public List<Sport> getAll() {
        return sportService.getAll();
    }

    @PostMapping
    public Sport add(@RequestParam String name) {
        return sportService.createOrGet(name);
    }

    @PostMapping("/json")
    public Sport addJson(@RequestBody SportCreateRequest req) {
        return sportService.createOrGet(req == null ? null : req.name);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        sportService.deleteWithAthletes(id);
        return "Deleted sport and related athletes";
    }
}