package switchisep.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import switchisep.project.datamodel.ProfileRequestJpa;
import switchisep.project.domain.valueobjects.ProfileRequestID;

@Service
public interface ProfileRequestJpaRepository extends JpaRepository<ProfileRequestJpa, ProfileRequestID> {

}

