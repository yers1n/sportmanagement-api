package sportmanagement.pool;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataPool<T> {

    private final Map<Long, T> data = new HashMap<>();

    public void put(Long id, T value) {
        data.put(id, value);
    }

    public List<T> getAll() {
        return new ArrayList<>(data.values());
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public List<T> filter(Predicate<T> predicate) {
        return data.values().stream()
                .filter(predicate)           // ← Lambda используется снаружи
                .collect(Collectors.toList());
    }

    public List<T> sort(Comparator<T> comparator) {
        return data.values().stream()
                .sorted(comparator)          // ← Lambda/Comparator
                .collect(Collectors.toList());
    }
}