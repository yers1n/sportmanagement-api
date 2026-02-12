package sportmanagement.pattern.factory;

import sportmanagement.entity.Sport;

public class SportFactory {

    private SportFactory() {}

    public static Sport create(String name) {
        return new Sport(name.trim());
    }
}