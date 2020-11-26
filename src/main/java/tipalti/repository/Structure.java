package tipalti.repository;

import org.springframework.stereotype.Repository;
import tipalti.model.Person;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class Structure {
    private final Map<Object, Set<Person>> neighboursByName = new HashMap<>();
    private final Map<Object, Set<Person>> neighboursByAddress = new HashMap<>();

    public Map<Object, Set<Person>> getNeighboursByName() {
        return neighboursByName;
    }

    public Map<Object, Set<Person>> getNeighboursByAddress() {
        return neighboursByAddress;
    }


    public void init(Person[] people) {
        neighboursByName.clear();
        neighboursByAddress.clear();
        for (Person person : people) {
            updateMap(neighboursByName, person.getFullName(), person, person.getFullName());
            updateMap(neighboursByAddress, person.getAddress(), person, person.getAddress());
        }
    }

    private void updateMap(Map<Object, Set<Person>> map, Object object, Person person, Object key) {
        if (map.containsKey(object)) {
            map.get(object).add(person);
        } else {
            Set<Person> insertSet = new HashSet() {{
                add(person);
            }};
            map.put(key, insertSet);
        }
    }
}
