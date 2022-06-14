package switchisep.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.ProfileDTO;
import switchisep.project.dto.assemblers.ProfileDTOAssembler;
import switchisep.project.domain.profile.ProfileFactoryInterface;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.profile.Profile;
import switchisep.project.repositories.interfaces.ProfileRepositoryInterface;

import java.util.Optional;

@Service
public class CreateProfileService {

    ProfileRepositoryInterface profileRepository;
    ProfileFactoryInterface profileFactoryInterface;
    ProfileDTOAssembler profileDTOAssembler;


    @Autowired
    public CreateProfileService(ProfileRepositoryInterface profileRepository,
                                ProfileFactoryInterface profileFactoryInterface,
                                ProfileDTOAssembler profileDTOAssembler) {

        this.profileRepository = profileRepository;
        this.profileFactoryInterface = profileFactoryInterface;
        this.profileDTOAssembler = profileDTOAssembler;

    }

    public Optional<ProfileDTO> createAndSaveProfile(ProfileDTO profileDTO) {
        Optional<ProfileDTO> response;
        if (profileRepository.existsByName(Name.createName(profileDTO.name))) {
            response=  Optional.empty();
        } else {
            Profile profileCreated = profileFactoryInterface
                    .createProfile(Name.createName(profileDTO.name));

            Profile profileSaved = profileRepository.save(profileCreated);
            ProfileDTO newProfileSaved =
                    profileDTOAssembler.toNative(profileSaved);
            response = Optional.of(newProfileSaved);
        }
        return response;
    }
}
