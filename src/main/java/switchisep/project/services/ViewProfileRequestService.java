package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.dto.ProfileRequestDTO;
import switchisep.project.dto.assemblers.ProfileRequestDTOAssembler;
import switchisep.project.domain.profilerequest.ProfileRequest;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.repositories.interfaces.ProfileRequestRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViewProfileRequestService {

        private final ProfileRequestRepositoryInterface profileRequestRepository;

        private final ProfileRequestDTOAssembler profileRequestDTOAssembler;

        public ViewProfileRequestService(ProfileRequestRepositoryInterface profileRequestRepository,
                                         ProfileRequestDTOAssembler profileRequestDTOAssembler) {

                this.profileRequestRepository = profileRequestRepository;
                this.profileRequestDTOAssembler = profileRequestDTOAssembler;
        }

        /**
         * Method that gets all available Profile Requests
         *
         * @return a List of all ProfileRequests converted to DTO.
         */

        public List<ProfileRequestDTO> getAllProfileRequests() {

                List<ProfileRequestDTO> profileRequestDTOS = new ArrayList<>();

                List<ProfileRequest> profileList = profileRequestRepository.findAll();

                for(ProfileRequest profileRequest : profileList) {

                        ProfileRequestDTO profileRequestDTO = profileRequestDTOAssembler.toDTO( profileRequest );

                        profileRequestDTOS.add(profileRequestDTO);
                }

                return profileRequestDTOS;
        }

        public Optional<ProfileRequestDTO> getProfileRequestById(int profileRequestId) {

                ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID(profileRequestId);

                Optional<ProfileRequest> profileRequest = profileRequestRepository.findById(profileRequestID);

                if (!profileRequest.isPresent()) {

                        return Optional.empty();
                }

                ProfileRequestDTO profileRequestDTO = profileRequestDTOAssembler.toDTO(profileRequest.get());

                return Optional.of(profileRequestDTO);
        }
}
