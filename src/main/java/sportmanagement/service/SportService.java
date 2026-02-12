package sportmanagement.service;

import sportmanagement.entity.Sport;
import java.util.List;

public interface SportService {
    List<Sport> getAll();
    Sport createOrGet(String name);
    void deleteWithAthletes(Long id);
}