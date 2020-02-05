package se.ecutb.data;

import org.springframework.stereotype.Service;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonRepositoryImpl implements PersonRepository {
    private List<Person> group = new ArrayList<>();

    @Override
    public Optional<Person> findById(int personId) {
        return group.stream().filter(person -> person.getPersonId()==personId)
                .findFirst();
    }

    @Override
    public Person persist(Person person) throws IllegalArgumentException {
        for (int i=0; i<group.size(); i++) {
            if (group.get(i).getEmail().equalsIgnoreCase(person.getEmail())) {
                throw new IllegalArgumentException();
            }
        }
        group.add(person);
        return person;
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return group.stream().filter(person -> person.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<Person> findByAddress(Address address) {
        List<Person> wantedAddress = new ArrayList<>();
        for (Person person: group) {
            if (Objects.equals(person.getAddress(), address)) {
                wantedAddress.add(person);
            }
        }
        return wantedAddress;
    }

    @Override
    public List<Person> findByCity(String city) {
        List<Person> wantedCity = new ArrayList<>();
        for (Person person: group) {
            if (Objects.isNull(person.getAddress())) {
                continue;
            }
            if (Objects.equals(person.getAddress().getCity(), city)) {
                wantedCity.add(person);
            }
        }
        return wantedCity;
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return group.stream().filter(person -> person.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByFullName(String fullName) {
        List<Person> wantedName = new ArrayList<>();
        for (Person person: group) {
            String name = person.getFirstName() + " " + person.getLastName();
            if (name.equalsIgnoreCase(fullName)) {
                wantedName.add(person);
            }
        }
        return wantedName;
    }

    @Override
    public List<Person> findAll() {
        return group;
    }

    @Override
    public boolean delete(int personId) throws IllegalArgumentException {
        for (Person person: group) {
            if(person.getPersonId()==personId) {
                group.remove(person);
                return true;
            }
            else if(!group.contains(personId)){
                throw new IllegalArgumentException();
            }
        }
        return false;
    }

    @Override
    public void clear() {
        group.clear();
    }
}
