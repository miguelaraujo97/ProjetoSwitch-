package switchisep.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
@ApiModel(description = "Model to create a new project")
public class ProjectDto extends RepresentationModel<ProjectDto> {

    @ApiModelProperty(notes = "Unique registration number for a project", example = "A11112")
    public String code;
    public String name;
    public String customer;
    public String projectDescription;
    public Double budget;
    public String typologyDescription;
    public Integer sprintDuration;
    public Integer numberOfPlannedSprints;
    public String status;
    public LocalDate startDate;
    public LocalDate endDate;
    public String businessSector;
}
