package switchisep.project.services;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import switchisep.project.dto.assemblers.UserDomainInternalDTOAssembler;
import switchisep.project.domain.user.UserFactoryInterface;
import switchisep.project.repositories.UserRepository;

@Profile("Service")
@Configuration
public class UserServiceTestConfig {

    @Bean
    @Primary
    public UserRepository UserRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    @Primary
    public UserDomainInternalDTOAssembler UserDomainDTOAssembler() {
        return Mockito.mock(UserDomainInternalDTOAssembler.class);
    }

    @Bean
    @Primary
    public UserFactoryInterface UserFactoryInterface() {
        return Mockito.mock(UserFactoryInterface.class);
    }

}
