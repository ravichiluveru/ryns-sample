package io.ryns.app.sample.common.action;

import java.util.List;

import io.ryns.platform.core.action.ActionManager;
import io.ryns.platform.core.impl.action.ActionManagerImpl;
import io.ryns.platform.core.repository.ValueRepository;
import io.ryns.platform.core.resource.ResourceManagerBinding;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-01
 */
@ResourceManagerBinding(value = ActionManager.class)
public class ActionManagerRsa extends ActionManagerImpl {

    public ActionManagerRsa() {
        super(() -> List.of(
                 ValueRepository.of(ActionsRsa.ALL.makeImmutableAndGet())
                // Utils.castOrNull(PersistenceRepository.of(ActionPersistable.class), ResourceRepository.class)
        ));
    }
}