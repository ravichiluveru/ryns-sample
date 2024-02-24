#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import io.ryns.platform.core.impl.resource.JavaResource;
import io.ryns.platform.core.resource.ResourceType;

#parse("File Header.java")

public class ${ResourceName}Impl extends JavaResource implements ${ResourceName} {

    private ${ResourceName}Impl(final Builder builder){
        super(builder);
    }
    public static class Builder extends JavaResource.Builder<${ResourceName}> {

        private Builder(final ResourceType resourceType, final String instanceId) {
            super(resourceType, instanceId);
        }

        public ${ResourceName}Impl build() {
            return new ${ResourceName}Impl(this);
        }
    }

    public static Builder builder(final ResourceType resourceType, final String instanceId) {
        return new Builder(resourceType, instanceId);
    }
}