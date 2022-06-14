package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.dto.ProfileDTO;
import switchisep.project.dto.assemblers.ProfileDTOAssembler;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.repositories.interfaces.ProfileRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViewProfilesService {

    private final ProfileRepositoryInterface profileRepository;

    private final ProfileDTOAssembler profileDTOAssembler;

    public ViewProfilesService(ProfileRepositoryInterface profileRepository, ProfileDTOAssembler profileDTOAssembler) {
        this.profileRepository = profileRepository;
        this.profileDTOAssembler = profileDTOAssembler;
    }

    public List<ProfileDTO> getAllProfiles() {

        List<ProfileDTO> profileDTOList = new ArrayList<>();

        List<Profile> profileList = profileRepository.getAllProfiles();

        for ( Profile profile : profileList) {

            ProfileDTO profileDTO = profileDTOAssembler.toNative( profile );

            profileDTOList.add(profileDTO);
        }

        return profileDTOList;
    }

    public Optional<ProfileDTO> getProfileById(String profileId) {

        ProfileID profileID = ProfileID.generateIdWithString(profileId);

        Optional<Profile> profileOptional = profileRepository.findById(profileID);

        if (!profileOptional.isPresent()) {

            return Optional.empty();
        }

        ProfileDTO profileDTO = profileDTOAssembler.toNative(profileOptional.get());

        return Optional.of(profileDTO);
    }
}
