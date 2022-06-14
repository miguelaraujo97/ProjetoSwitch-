package switchisep.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchisep.project.datamodel.SprintJpa;
import switchisep.project.datamodel.assemblers.SprintDomainDataAssembler;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.SprintID;
import switchisep.project.repositories.interfaces.SprintRepositoryInterface;
import switchisep.project.repositories.jpa.SprintJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SprintRepository implements SprintRepositoryInterface {

    @Autowired
    SprintJpaRepository sprintJpaRepository;
    @Autowired
    SprintDomainDataAssembler sprintDomainDataAssembler;

    public SprintRepository() {
    //Empty constructor
    }

    public Sprint save(Sprint sprint) {

        SprintJpa sprintJPA =
                sprintDomainDataAssembler.toData(sprint);
        SprintJpa savedSprintJpa = sprintJpaRepository.save(sprintJPA);

        return sprintDomainDataAssembler.toDomain(savedSprintJpa);
    }

    public List<Sprint> findAll() {
        List<SprintJpa> listJpa =
                sprintJpaRepository.findAll();
        List<Sprint> listSprint = new ArrayList<>();
        for (SprintJpa spr : listJpa) {
            listSprint.add(sprintDomainDataAssembler.toDomain(
                    spr));
        }
        return listSprint;
    }

    public List<Sprint> findSprintsByProjectCode(ProjectCode projectCode) {
        List<SprintJpa> listJpa =
                sprintJpaRepository.findAll();

        List<Sprint> listSprint = new ArrayList<>();

        for (SprintJpa sprJpa : listJpa) {
            if (sprJpa.getProjectCode().equals(projectCode)) {
                listSprint.add(sprintDomainDataAssembler.toDomain(
                        sprJpa));
            }
        }
        return listSprint;
    }

    public Optional<Sprint> findBySprintID(SprintID sprintID) {
        Optional<SprintJpa> sprintJPA =
                sprintJpaRepository.findBySprintID(sprintID);
        if (sprintJPA.isPresent()) {
            Sprint sprint = sprintDomainDataAssembler.toDomain(
                    sprintJPA.get());
            return Optional.of(sprint);
        }
        return Optional.empty();

    }

    public void deleteAll() {
        sprintJpaRepository.deleteAll();
        ;
    }
}
