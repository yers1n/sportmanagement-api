package sportmanagement.controller;

import org.springframework.web.bind.annotation.*;
import sportmanagement.dao.AthleteJdbcDao;
import sportmanagement.entity.Athlete;

import java.util.List;

@RestController
@RequestMapping("/api/jdbc/athletes")
public class JdbcDemoController {

    private final AthleteJdbcDao jdbcDao;

    public JdbcDemoController(AthleteJdbcDao jdbcDao) {
        this.jdbcDao = jdbcDao;
    }

    @GetMapping
    public List<Athlete> all() {
        return jdbcDao.findAll();
    }

    @PostMapping
    public String create(@RequestParam String name,
                         @RequestParam int age,
                         @RequestParam int rank,
                         @RequestParam long sportId) {
        jdbcDao.create(name, age, rank, sportId);
        return "Created via JDBC";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        jdbcDao.delete(id);
        return "Deleted via JDBC";
    }
}