#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import io.ryns.platform.core.request.RequestContext;
import io.ryns.platform.core.service.Service;
import io.ryns.platform.core.service.ServiceType;
import io.ryns.platform.core.service.Services;

#parse("File Header.java")

public interface ${NAME}Service extends Service {
    ServiceType<${NAME}Service> BINDING = ServiceType.of(${NAME}Service.class);

    static ${NAME}Service get(final RequestContext rc) {
        return Services.get(rc, BINDING);
    }
}