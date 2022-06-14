package switchisep.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchisep.project.datamodel.ProfileJpa;
import switchisep.project.datamodel.assemblers.ProfileDomainDataAssembler;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.repositories.interfaces.ProfileRepositoryInterface;
import switchisep.project.repositories.jpa.ProfileJpaRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfileRepository implements ProfileRepositoryInterface {

    ProfileJpaRepository profileJpaRepository;
    ProfileDomainDataAssembler profileDomainDataAssembler;

    @Autowired
    public ProfileRepository(ProfileJpaRepository profileJpaRepository,
                             ProfileDomainDataAssembler profileDomainDataAssembler) {

        this.profileJpaRepository = profileJpaRepository;
        this.profileDomainDataAssembler = profileDomainDataAssembler;
    }

    public Profile save(Profile profile) {

        ProfileJpa profileJpa = profileDomainDataAssembler.toData(profile);
        ProfileJpa savedProfileJpa = profileJpaRepository.save(profileJpa);

        return profileDomainDataAssembler.toDomain(savedProfileJpa);
    }

    @Transactional
    public Optional<Profile> findByName(Name profileName) {

        Optional<ProfileJpa> optionalProfileJpa =
                profileJpaRepository.findByName(profileName);

        if (optionalProfileJpa.isPresent()) {

            ProfileJpa profileJpa = optionalProfileJpa.get();
            Profile profile = profileDomainDataAssembler.toDomain(profileJpa);

            return Optional.of(profile);

        } else {

            return Optional.empty();
        }
    }

    public boolean existsByName(Name profileName) {
        return profileJpaRepository.existsByName(profileName);
    }

    public List<Profile> getAllProfiles() {

        List<ProfileJpa> listProfileJpa = profileJpaRepository.findAll();

        List<Profile> profileList = new ArrayList<>();

        for (ProfileJpa profileJpa : listProfileJpa) {

            Profile profile = profileDomainDataAssembler.toDomain(profileJpa);

            profileList.add(profile);
        }

        return profileList;
    }

    public Optional<Profile> findById(ProfileID profileID) {

        Optional<ProfileJpa> profileJpaOptional = profileJpaRepository.findById(profileID);

        if (!profileJpaOptional.isPresent()) {

            return Optional.empty();
        }

        Profile profile = profileDomainDataAssembler.toDomain(profileJpaOptional.get());

        return Optional.of(profile);
    }

    public void deleteAll() {
        profileJpaRepository.deleteAll();
    }

}
