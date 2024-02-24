package io.ryns.app.sample.common.resource;

import io.ryns.platform.core.impl.resource.ResourceTypeManagerImpl;
import io.ryns.platform.core.repository.ValueRepository;
import io.ryns.platform.core.resource.ResourceManagerBinding;
import io.ryns.platform.core.resource.ResourceTypeManager;

import java.util.List;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-16
 */
@ResourceManagerBinding(value = ResourceTypeManager.class)
public class ResourceTypeManagerRsa extends ResourceTypeManagerImpl {

    public ResourceTypeManagerRsa() {
        super(() -> List.of(
                ValueRepository.of(ResourceTypesRsa.ALL.makeImmutableAndGet())
                // Utils.castOrNull(PersistenceRepository.of(ResourceTypePersistable.class), ResourceRepository.class)
        ));
    }
}