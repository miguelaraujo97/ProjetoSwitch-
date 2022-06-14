package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
public class ResourceID implements Serializable {

    private static final long serialVersionUID = 1850353019088555065L;
    private String resourceID;

    public ResourceID() {
    }

    private ResourceID(String resourceID) {

        this.resourceID = resourceID;
    }

    /**
     * @author Celso Castro 1211755
     *
     * Static method to instanciate a ResourceID VO
     *
     * @return a unique Resource Identifier
     */

    public static ResourceID createResourceID(String resourceID) {

        if (resourceID == null || resourceID.isEmpty()) {

            throw new IllegalArgumentException("Resource ID Cannot Be Null Or Blank");
        }

        return new ResourceID(resourceID);
    }

    public String getResourceID() {
        return resourceID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceID that = (ResourceID) o;
        return Objects.equals(resourceID, that.resourceID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceID);
    }
}
