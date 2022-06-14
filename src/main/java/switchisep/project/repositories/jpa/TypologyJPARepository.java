package switchisep.project.repositories.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import switchisep.project.datamodel.TypologyJpa;
import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.TypologyID;

import java.util.List;
import java.util.Optional;


@Repository
public interface TypologyJPARepository extends JpaRepository<TypologyJpa, TypologyID> {

        boolean existsByTypologyDescription(Description typologyDescription);

        Optional<TypologyJpa> findById(TypologyID typologyID);

        List<TypologyJpa> findAll();
}
