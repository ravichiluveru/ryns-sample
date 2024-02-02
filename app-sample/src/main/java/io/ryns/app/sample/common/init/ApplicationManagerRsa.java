package io.ryns.app.sample.common.init;

import io.ryns.platform.core.application.ApplicationManager;
import io.ryns.platform.core.impl.application.ApplicationManagerImpl;
import io.ryns.platform.core.repository.ValueRepository;
import io.ryns.platform.core.resource.ResourceManagerBinding;

import java.util.List;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-01-26
 */
@ResourceManagerBinding(value = ApplicationManager.class)
public class ApplicationManagerRsa extends ApplicationManagerImpl {

    public ApplicationManagerRsa() {
        super(() -> List.of(
                ValueRepository.of(ApplicationsRsa.ALL.makeImmutableAndGet())
                // Utils.castOrNull(PersistenceRepository.of(ApplicationPersistable.class), ResourceRepository.class)
        ));
    }
}