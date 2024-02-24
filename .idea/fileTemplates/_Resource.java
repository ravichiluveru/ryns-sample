#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import io.ryns.platform.core.resource.Resource;

#parse("File Header.java")
public interface ${NAME} extends Resource {

    Fields.I FIELDS = new Fields.I();

    interface Fields<Q extends ${NAME}>
        extends Resource.Fields<Q> {

        class I
            implements Fields<${NAME}> {
            private I() {
            }
        }
    }
}