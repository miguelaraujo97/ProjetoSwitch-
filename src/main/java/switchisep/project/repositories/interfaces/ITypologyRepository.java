package switchisep.project.repositories.interfaces;

import org.springframework.stereotype.Service;
import switchisep.project.domain.typology.Typology;
import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.TypologyID;

import java.util.List;
import java.util.Optional;

@Service
public interface ITypologyRepository {

    Optional<Typology> save(Typology typology);

    boolean existsByTypologyDescription(Description typologyDescription);

    Optional<Typology> findTypologyById(TypologyID typologyID);

    List<Typology> findAllTypologies();

    void deleteAll();


}
