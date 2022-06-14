package switchisep.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchisep.project.datamodel.ProfileRequestJpa;
import switchisep.project.datamodel.assemblers.ProfileRequestDomainDataAssembler;
import switchisep.project.domain.profilerequest.ProfileRequest;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.repositories.interfaces.ProfileRequestRepositoryInterface;
import switchisep.project.repositories.jpa.ProfileRequestJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfileRequestRepository implements ProfileRequestRepositoryInterface {

    @Autowired
    ProfileRequestJpaRepository profileRequestJpaRepository;
    @Autowired
    ProfileRequestDomainDataAssembler profileRequestDomainDataAssembler;

    public ProfileRequestRepository() {
        //Empty constructor
    }

    public ProfileRequest save(ProfileRequest profileRequest) {

        ProfileRequestJpa profileRequestJpa =
                profileRequestDomainDataAssembler.toData(profileRequest);
        ProfileRequestJpa savedProfileJpa = profileRequestJpaRepository.save(profileRequestJpa);

        return profileRequestDomainDataAssembler.toDomain(savedProfileJpa);
    }

    public List<ProfileRequest> findAll() {
        List<ProfileRequestJpa> listJpa =
                profileRequestJpaRepository.findAll();
        List<ProfileRequest> listPrReq = new ArrayList<>();
        for (ProfileRequestJpa prRJpa : listJpa) {
            listPrReq.add(profileRequestDomainDataAssembler.toDomain(
                    prRJpa));
        }
        return listPrReq;
    }

    public Optional<ProfileRequest> findById(ProfileRequestID profileRequestID) {

        Optional<ProfileRequestJpa> optionalProfileRequestJpa =
                profileRequestJpaRepository.findById(profileRequestID);

        if (!optionalProfileRequestJpa.isPresent()) {
            return Optional.empty();
        }

        ProfileRequest profileRequest = profileRequestDomainDataAssembler
                .toDomain(optionalProfileRequestJpa.get());

        return Optional.of(profileRequest);
    }


}
