#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import io.ryns.platform.core.loader.LoadOnStartup;
import io.ryns.platform.core.resource.ResourceType;
import io.ryns.platform.core.util.ListCollector;

#parse("File Header.java")

@LoadOnStartup
public interface ${ResourceName}s${AppCode} {

    ResourceType RT = ResourceTypes${AppCode}.${ResourceName}_${AppCode};
    ListCollector<${ResourceName}> ALL = new ListCollector<>();

    // ${ResourceName} FIRST = ALL.add(${ResourceName}Impl.builder(RT, "1"));

    static void init() {
        //Intentional empty method for loading this class during startup with above @LoadOnStartup annotation.
    }
}