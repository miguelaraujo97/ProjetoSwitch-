package switchisep.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchisep.project.datamodel.UserStoryJpa;
import switchisep.project.datamodel.assemblers.UserStoryDomainDataAssembler;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.UserStoryID;
import switchisep.project.repositories.interfaces.IUserStoryRepository;
import switchisep.project.repositories.jpa.UserStoryJpaRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserStoryRepository implements IUserStoryRepository {


    UserStoryJpaRepository userStoryJpaRepository;
    UserStoryDomainDataAssembler userStoryAssembler;

    @Autowired
    public UserStoryRepository(UserStoryJpaRepository userStoryJpaRepository, UserStoryDomainDataAssembler userStoryAssembler) {
        this.userStoryJpaRepository = userStoryJpaRepository;
        this.userStoryAssembler = userStoryAssembler;
    }

    public UserStory save(UserStory userStory) {
        UserStoryJpa userStoryJpa = userStoryAssembler.toData(userStory);

        UserStoryJpa savedUserStoryJpa =
                userStoryJpaRepository.save(userStoryJpa);

        return userStoryAssembler.toDomain(savedUserStoryJpa);
    }

    @Transactional
    public List<UserStory> findAllByProjectCode(ProjectCode projectCode) {

        List<UserStory> productBacklog = new ArrayList<>();

        List<UserStoryJpa> productBacklogJpa =
                userStoryJpaRepository.findAllByProjectCode(projectCode.getCode());

        for (UserStoryJpa userStoryJpa : productBacklogJpa) {

            UserStory userStory = userStoryAssembler.toDomain(userStoryJpa);

            productBacklog.add(userStory);

        }

        return productBacklog;
    }

    public Optional<UserStory> findByUserStoryID(
            UserStoryID userStoryID) {
        Optional<UserStoryJpa> userStoryJpa =
                userStoryJpaRepository.findByUserStoryID(userStoryID.getUserStoryID());
        if (userStoryJpa.isPresent()) {
            UserStory userStory = userStoryAssembler.toDomain(
                    userStoryJpa.get());
            return Optional.of(userStory);
        }
        return Optional.empty();
    }

    public void deleteAll(){
        userStoryJpaRepository.deleteAll();
    }
}

