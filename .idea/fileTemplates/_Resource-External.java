#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import io.ryns.platform.core.repository.ExternalResource;
import io.ryns.platform.core.resource.ResourceExternal;

#parse("File Header.java")

public class ${NAME}ExternalResource extends ExternalResource implements ${NAME} {

    public static ${NAME}ExternalResource of(ResourceExternal resourceExternal) {
        return new ${NAME}ExternalResource(resourceExternal);
    }

    private ${NAME}ExternalResource(ResourceExternal resourceExternal) {
        super(resourceExternal);
    }

}