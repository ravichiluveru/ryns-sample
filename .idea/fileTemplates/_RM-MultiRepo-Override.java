#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import java.util.List;

import io.ryns.platform.core.resource.ResourceManagerBinding;

#parse("File Header.java")

@ResourceManagerBinding(value = ${ResourceName}Manager.class)
public class ${ResourceName}Manager${AppCode} extends ${ResourceName}ManagerImpl {

    public ${ResourceName}Manager${AppCode}() {
        super(() -> List.of(
            // ValueRepository.of(${ResourceName}s${AppCode}.ALL.makeImmutableAndGet())
            // Utils.castOrNull(PersistenceRepository.of(${ResourceName}Persistable.class), ResourceRepository.class)            
        ));
    }   
}