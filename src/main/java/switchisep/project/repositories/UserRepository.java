package switchisep.project.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchisep.project.datamodel.UserJpa;
import switchisep.project.datamodel.assemblers.UserDomainDataAssembler;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;
import switchisep.project.repositories.jpa.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements UserRepositoryInterface {

    UserJpaRepository userJpaRepository;
    UserDomainDataAssembler userAssembler;

    @Autowired
    public UserRepository(UserJpaRepository userJpaRepository, UserDomainDataAssembler userAssembler) {
        this.userJpaRepository = userJpaRepository;
        this.userAssembler = userAssembler;
    }


    public User save(User user) {
        UserJpa userJpa = userAssembler.toData(user);

        UserJpa savedPersonJpa = userJpaRepository.save(userJpa);

        return userAssembler.toDomain(savedPersonJpa);
    }

    public List<User> findAll() {
        List<UserJpa> opUserJpa = userJpaRepository.findAll();

        List<User> setUser = new ArrayList<>();
        for (UserJpa userJpa : opUserJpa) {
            User user = userAssembler.toDomain(userJpa);
            setUser.add(user);
        }

        return setUser;
    }

    public Optional<User> findById(UserID id) {
        Optional<UserJpa> opUserJpa = userJpaRepository.findById(id);

        if (opUserJpa.isPresent()) {
            UserJpa userJpa = opUserJpa.get();

            User user = userAssembler.toDomain(userJpa);
            return Optional.of(user);
        } else
            return Optional.empty();
    }

    public Optional<User> findByEmail(Email email) {
        Optional<UserJpa> opUserJpa = userJpaRepository.findByEmail(email);

        if (opUserJpa.isPresent()) {

            User user = userAssembler.toDomain(opUserJpa.get());
            return Optional.of(user);
        } else
            return Optional.empty();
    }

    public void deleteAll(){
        userJpaRepository.deleteAll();
    }

    public List<User> findByProfileID(ProfileID profileID){
        List<UserJpa> listUserJpa = userJpaRepository.findByProfileID(profileID);

        List<User> userListByProfileID = new ArrayList<>();
        for (UserJpa userJpa : listUserJpa) {
            User user = userAssembler.toDomain(userJpa);
            userListByProfileID.add(user);
        }

        return userListByProfileID;
    }

}