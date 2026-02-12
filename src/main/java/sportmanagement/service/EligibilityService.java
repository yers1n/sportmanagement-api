package sportmanagement.service;

import sportmanagement.entity.Eligible;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityService {

    public long countEligible(List<? extends Eligible> list) {
        return list.stream().filter(Eligible::isEligible).count();
    }
}