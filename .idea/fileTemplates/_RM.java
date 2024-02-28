#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import io.ryns.platform.core.request.RequestContext;
import io.ryns.platform.core.resource.ResourceManager;
import io.ryns.platform.core.resource.ResourceManagers;
import io.ryns.platform.core.util.BindingType;

#parse("File Header.java")

public interface ${ResourceName}Manager extends ResourceManager<${ResourceName}> {
    BindingType<${ResourceName}Manager> BINDING = BindingType.of(${ResourceName}Manager.class);

    static ${ResourceName}Manager get(final RequestContext rc) {
        return ResourceManagers.get(rc, BINDING);
    }
    
    @Override
    default ${ResourceName}.Fields<${ResourceName}> getFields() {
        return ${ResourceName}.FIELDS;
    }
}