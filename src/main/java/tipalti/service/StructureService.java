package tipalti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tipalti.model.Person;
import tipalti.repository.Structure;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class StructureService {
    private static final int MAX_PATH_STEP = Integer.MAX_VALUE;
    private final Structure structure;
    Set<Person> currentPersonAndOtherThatAlreadyCheck = new HashSet<>();


    @Autowired
    public StructureService(Structure structured) {
        this.structure = structured;
    }


    public void init(Person[] people) {
        structure.init(people);
    }


    public int findMinRelationLevel(Person personA, Person personB) {
        currentPersonAndOtherThatAlreadyCheck.clear();
        return findMinRelationLevel(personA, personB, 0).orElse(-1);
    }


    private Optional<Integer> findMinRelationLevel(Person personA, Person personB, int steps) {
        currentPersonAndOtherThatAlreadyCheck.add(personA);
        if (personA.equals(personB)) {
            return Optional.of(steps);
        }

        Set<Person> neighboursByNameOfPersonA = structure.getNeighboursByName().getOrDefault(personA.getFullName(), new HashSet());
        Set<Person> neighboursByAddressOfPersonA = structure.getNeighboursByAddress().getOrDefault(personA.getAddress(), new HashSet<>());
        Set<Person> mergeSets = mergeSets(neighboursByNameOfPersonA, neighboursByAddressOfPersonA);

        if (mergeSets.isEmpty()) {
            return Optional.empty();
        }

        if (mergeSets.contains(personB)) {
            return Optional.of(steps + 1);
        } else {
            int minimumPath = MAX_PATH_STEP;
            for (Person person : mergeSets) {
                Optional<Integer> iterationResult = findMinRelationLevel(person, personB, steps + 1);
                if (iterationResult.isPresent() && minimumPath > iterationResult.get()) {
                    minimumPath = iterationResult.get();
                }
            }

            if (minimumPath < MAX_PATH_STEP) {
                return Optional.of(minimumPath);
            }
            return Optional.empty();
        }
    }


    private Set mergeSets(Set neighboursByName, Set neighboursByAddress) {
        Set<Person> mergeSets = new HashSet<>();
        mergeSets.addAll(neighboursByName);
        mergeSets.addAll(neighboursByAddress);
        mergeSets.removeAll(currentPersonAndOtherThatAlreadyCheck);
        return mergeSets;
    }
}


