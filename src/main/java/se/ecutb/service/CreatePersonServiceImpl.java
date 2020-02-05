package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ecutb.data.IdSequencers;
import se.ecutb.data.IdSequencersImpl;
import se.ecutb.model.AbstractPersonFactory;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.Objects;

@Service
public class CreatePersonServiceImpl extends AbstractPersonFactory implements CreatePersonService {
    private IdSequencersImpl sequencers = new IdSequencersImpl();

    @Autowired
    public CreatePersonServiceImpl() {
    }

    @Override
    public Person create(String firstName, String lastName, String email) throws IllegalArgumentException {
        if (firstName == null || lastName == null || email == null) {
            throw new IllegalArgumentException();
        }
        return createNewPerson(sequencers.nextPersonId(), firstName, lastName, email, null);
    }

    @Override
    public Person create(String firstName, String lastName, String email, Address address) throws IllegalArgumentException {
        if (firstName == null || lastName == null || email == null) {
            throw new IllegalArgumentException();
        }
        return createNewPerson(sequencers.nextPersonId(), firstName, lastName, email, address);
    }
}
