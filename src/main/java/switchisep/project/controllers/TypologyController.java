package switchisep.project.controllers;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import switchisep.project.ApiConfig;
import switchisep.project.dto.TypologyCreationDTO;
import switchisep.project.dto.TypologyDTO;
import switchisep.project.services.CreateTypologyService;
import switchisep.project.services.ViewTypologiesService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/typologies")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name= ApiConfig.TAG_TYPOLOGY,description=ApiConfig.TAG_TYPOLOGY_MESSAGE)
public class TypologyController {

    @Autowired
    private CreateTypologyService createTypologyService;

    @Autowired
    private ViewTypologiesService viewTypologiesService ;

    /**
     * Method that gets a Typology by its ID
     *
     * @param typologyId The ID of the requested Typology
     * @return The requested Typology when successful otherwise return a error message.
     */

    @GetMapping(path = "/{typologyId}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity<Object> getTypologyById(@PathVariable(value = "typologyId") String typologyId) {

        Optional<TypologyDTO> optionalTypologyDTO = viewTypologiesService.getTypologyById(typologyId);

        if (!optionalTypologyDTO.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"No Typology found with that Id.\"}");
        }

        TypologyDTO typologyDTO = optionalTypologyDTO.get();

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(typologyDTO);
    }

    /**
     * Method that gets all the available Typologies.
     *
     * @return A List with available Typologies. If the list is empty returns a message.
     */

    @GetMapping( headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity<Object> getAllTypologies() {

        List<TypologyDTO> typologyDTOList = viewTypologiesService.getAllTypologies();

        if (typologyDTOList.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"There are no created Typologies \"}");
        }

        for (TypologyDTO typologyDTO : typologyDTOList) {

            Link typologyLink = linkTo(methodOn(TypologyController.class)
                    .getTypologyById(typologyDTO.typologyId)).withSelfRel().withType("GET");

            typologyDTO.add(typologyLink);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(typologyDTOList);
    }

    /**
     * Method that creates a new Typology.
     *
     * @param typologyCreationDTO User input data DTO that contains the new Typology name/description.
     * @return The created Typology when successful otherwise it sends a error message.
     */

    @PostMapping()
    @ResponseBody
    @ApiOperation(value = "US012 Create Typology", tags = ApiConfig.TAG_TYPOLOGY)
    public ResponseEntity<Object> createTypology(@RequestBody TypologyCreationDTO typologyCreationDTO){

        if(Objects.nonNull(typologyCreationDTO) && !typologyCreationDTO.getDescription().isEmpty()
                && !typologyCreationDTO.getDescription().isBlank()){

        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("Empty or null data");

        Optional<TypologyDTO> opTypologyDTO = createTypologyService.createTypologyAndSave(typologyCreationDTO);

        if (!opTypologyDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON).body("Typology Already Exists");
        }

        TypologyDTO typologyDTO = opTypologyDTO.get();

        Link typologyLink = linkTo(methodOn(TypologyController.class)
                .getTypologyById(typologyDTO.typologyId)).withSelfRel().withType("GET");

        typologyDTO.add(typologyLink);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(opTypologyDTO);
    }

}
