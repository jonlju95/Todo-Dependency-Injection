package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ecutb.dto.PersonDto;
import se.ecutb.dto.PersonDtoWithTodo;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private List<Person> group = new ArrayList<>();
    private CreatePersonService createPersonService;
    private PersonDtoConversionService conversionService;

    @Override
    public Person createPerson(String firstName, String lastName, String email, Address address) {
        Person person;
        person = createPersonService.create(firstName, lastName, email, address);
        group.add(person);
        return person;
    }

    @Override
    public List<PersonDto> findAll() {
        List<PersonDto> groupDto = new ArrayList<>();
        for (Person person: group) {
            groupDto.add(conversionService.convertToPersonDto(person));
        }
        return groupDto;
    }

    @Override
    public PersonDto findById(int personId) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Person findByEmail(String email) throws IllegalArgumentException {
        return null;
    }

    @Override
    public List<PersonDtoWithTodo> findPeopleWithAssignedTodos() {
        return null;
    }

    @Override
    public List<PersonDto> findAllPeopleWithNoTodos() {
        return null;
    }

    @Override
    public List<PersonDto> findPeopleByAddress(Address address) {
        return null;
    }

    @Override
    public List<PersonDto> findPeopleByCity(String city) {
        return null;
    }

    @Override
    public List<PersonDto> findByFullName(String fullName) {
        return null;
    }

    @Override
    public List<PersonDto> findByLastName(String lastName) {
        return null;
    }

    @Override
    public boolean deletePerson(int personId) throws IllegalArgumentException {
        return false;
    }
}
