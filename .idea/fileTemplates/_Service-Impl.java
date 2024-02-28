#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import io.ryns.platform.core.service.RequestContext;
import io.ryns.platform.core.service.Service;
import io.ryns.platform.core.service.ServiceBinding;

#parse("File Header.java")

@ServiceBinding(service = Service.DEFAULT, value = ${Service}Service.class)
public class ${Service}ServiceImpl implements ${Service}Service {
}