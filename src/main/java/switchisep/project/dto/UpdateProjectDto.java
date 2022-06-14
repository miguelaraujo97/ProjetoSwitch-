package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class UpdateProjectDto extends RepresentationModel<UpdateProjectDto>{

        public String code;
        public String projectDescription;
        public Integer sprintDuration;
        public Integer numberOfPlannedSprints;
        public String status;
        public LocalDate startDate;
        public LocalDate endDate;
    }

