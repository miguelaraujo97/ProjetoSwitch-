package switchisep.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchisep.project.datamodel.TypologyJpa;
import switchisep.project.datamodel.assemblers.TypologyDomainDataAssembler;
import switchisep.project.domain.typology.Typology;
import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.TypologyID;
import switchisep.project.repositories.interfaces.ITypologyRepository;
import switchisep.project.repositories.jpa.TypologyJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TypologyRepository implements ITypologyRepository {

    @Autowired
    TypologyJPARepository typologyJPARepository;

    @Autowired
    TypologyDomainDataAssembler typologyDomainDataAssembler;

    public Optional<Typology> save(Typology typology){

        TypologyJpa typologyJPA = typologyDomainDataAssembler.toData(typology);

        TypologyJpa savedTypologyJPA = typologyJPARepository.save(typologyJPA);

        return Optional.of(typologyDomainDataAssembler.toDomain(savedTypologyJPA));
    }

    public boolean existsByTypologyDescription(Description typologyDescription){

        return typologyJPARepository.existsByTypologyDescription(typologyDescription);

    }

    /**
     * Method that finds a Typology by its Identification Number.
     *
     * @param typologyID The Identification Number to get a specific Typology.
     * @return The requested Typology
     */

    public Optional<Typology> findTypologyById(TypologyID typologyID) {

        Optional<TypologyJpa> optionalTypologyJpa = typologyJPARepository.findById(typologyID);

        if (!optionalTypologyJpa.isPresent()) {

            return Optional.empty();
        }

        Typology typology = typologyDomainDataAssembler.toDomain(optionalTypologyJpa.get());

        return Optional.of(typology);
    }

    /**
     * Method that finds all available typologies stored in the database.
     *
     * @return A List of all the available Typologies.
     */

    public List<Typology> findAllTypologies() {

        List<Typology> typologyList = new ArrayList<>();

        List<TypologyJpa> typologyJpaList = typologyJPARepository.findAll();

        for (TypologyJpa typologyJpa : typologyJpaList) {

            Typology typology = typologyDomainDataAssembler.toDomain(typologyJpa);

            typologyList.add(typology);
        }

        return typologyList;

    }

    public void deleteAll(){
        typologyJPARepository.deleteAll();
    }
}
