package switchisep.project.repositories.interfaces;

import org.springframework.stereotype.Service;
import switchisep.project.domain.profilerequest.ProfileRequest;
import switchisep.project.domain.valueobjects.ProfileRequestID;

import java.util.List;
import java.util.Optional;

@Service
public interface ProfileRequestRepositoryInterface {

    ProfileRequest save(ProfileRequest profileRequest);

    List<ProfileRequest> findAll();

    Optional<ProfileRequest> findById(ProfileRequestID profileRequestID);

}
