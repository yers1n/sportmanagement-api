package sportmanagement.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sportmanagement.entity.Sport;
import sportmanagement.exception.NotFoundException;
import sportmanagement.exception.ValidationException;
import sportmanagement.repo.AthleteRepository;
import sportmanagement.repo.SportRepository;
import sportmanagement.service.SportService;

import java.util.List;

@Service
public class SportServiceImpl implements SportService {

    private final SportRepository sportRepo;
    private final AthleteRepository athleteRepo;

    public SportServiceImpl(SportRepository sportRepo, AthleteRepository athleteRepo) {
        this.sportRepo = sportRepo;
        this.athleteRepo = athleteRepo;
    }

    @Override
    public List<Sport> getAll() {
        return sportRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Sport createOrGet(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        String n = name.trim();
        Sport sport = sportRepo.findByNameIgnoreCase(n)
                .orElseGet(() -> sportmanagement.pattern.factory.SportFactory.create(n));
        return sportRepo.save(sport);
    }

    @Transactional
    @Override
    public void deleteWithAthletes(Long id) {
        if (!sportRepo.existsById(id)) throw new NotFoundException("Sport not found");
        athleteRepo.deleteBySport_Id(id);
        sportRepo.deleteById(id);
    }
}