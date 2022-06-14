package switchisep.project.domain.profile;

import org.springframework.stereotype.Service;
import switchisep.project.domain.valueobjects.Name;

@Service
public interface ProfileFactoryInterface {
    Profile createProfile(Name profileName);
}
