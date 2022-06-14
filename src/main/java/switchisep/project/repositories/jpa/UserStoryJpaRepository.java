package switchisep.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import switchisep.project.datamodel.UserStoryJpa;
import switchisep.project.domain.valueobjects.UserStoryID;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserStoryJpaRepository extends JpaRepository<UserStoryJpa, UserStoryID> {

    List<UserStoryJpa> findAllByProjectCode(String projectCode);

    Optional<UserStoryJpa> findByUserStoryID(String userStoryID);
}
