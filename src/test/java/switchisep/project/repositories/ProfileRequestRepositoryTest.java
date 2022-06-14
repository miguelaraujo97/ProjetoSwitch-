package switchisep.project.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.datamodel.ProfileRequestJpa;
import switchisep.project.datamodel.assemblers.ProfileRequestDomainDataAssembler;
import switchisep.project.domain.profilerequest.ProfileRequest;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.repositories.jpa.ProfileRequestJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileRequestRepositoryTest {

    @InjectMocks
    ProfileRequestRepository profileRequestRepository;

    @Mock
    ProfileRequestJpaRepository profileRequestJpaRepository;

    @Mock
    ProfileRequestDomainDataAssembler profileRequestDomainDataAssembler;

    @Test
    void saveProfileRequest(){
        //Arrange
        ProfileRequest profileRequestMock = mock(ProfileRequest.class);
        ProfileRequestJpa profileRequestJpaMock = mock(ProfileRequestJpa.class);

        when(profileRequestDomainDataAssembler.toData(profileRequestMock)).thenReturn(profileRequestJpaMock);
        when(profileRequestJpaRepository.save(profileRequestJpaMock)).thenReturn( profileRequestJpaMock);
        when(profileRequestDomainDataAssembler.toDomain(profileRequestJpaMock)).thenReturn(profileRequestMock);

        //Act
        ProfileRequest result = profileRequestRepository.save(profileRequestMock);

        //Assert
        assertEquals(profileRequestMock, result);
    }

    @Test
    void findAllProfileRequest(){
        //Arrange
        List<ProfileRequestJpa> listJpa = new ArrayList<>();
        List<ProfileRequest> listPrReq = new ArrayList<>();

        ProfileRequest profileRequestMock = mock(ProfileRequest.class);
        ProfileRequestJpa profileRequestJpaMock = mock(ProfileRequestJpa.class);

        listJpa.add(profileRequestJpaMock);
        listPrReq.add(profileRequestMock);

        when(profileRequestJpaRepository.findAll()).thenReturn(listJpa);
        when(profileRequestDomainDataAssembler.toDomain(any(ProfileRequestJpa.class))).thenReturn(profileRequestMock);

        //Act

        List<ProfileRequest> result = profileRequestRepository.findAll();

        //Assert
        assertEquals(listPrReq, result);
    }

    @Test
    void shouldFindProfileRequestById() {

        // Arrange

        ProfileRequest profileRequestMock = mock(ProfileRequest.class);

        Optional<ProfileRequest> profileRequestOptional = Optional.of(profileRequestMock);

        ProfileRequestJpa profileRequestJpaMock = mock(ProfileRequestJpa.class);

        Optional<ProfileRequestJpa> profileRequestJpaOptional = Optional.of(profileRequestJpaMock);

        ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID(1);

        when(profileRequestJpaRepository.findById(profileRequestID)).thenReturn(profileRequestJpaOptional);

        when(profileRequestDomainDataAssembler.toDomain(profileRequestJpaMock)).thenReturn(profileRequestMock);

        // Act

        Optional<ProfileRequest> profileRequestResult = profileRequestRepository.findById(profileRequestID);

        // Assert

        assertEquals(profileRequestOptional, profileRequestResult);

    }

    @Test
    void shouldNotFindProfileRequestByIdWrongId() {


        // Arrange

        ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID(1);

        when(profileRequestJpaRepository.findById(profileRequestID)).thenReturn(Optional.empty());

        // Act

        Optional<ProfileRequest> profileRequestResult = profileRequestRepository.findById(profileRequestID);

        // Assert

        assertEquals(Optional.empty(), profileRequestResult);
    }


}