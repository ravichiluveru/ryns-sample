package io.ryns.app.sample.common.user;

import io.ryns.platform.core.impl.user.UserManagerImpl;
import io.ryns.platform.core.repository.ValueRepository;
import io.ryns.platform.core.resource.ResourceManagerBinding;
import io.ryns.platform.core.user.UserManager;

import java.util.List;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-18
 */
@ResourceManagerBinding(value = UserManager.class)
public class UserManagerRsa extends UserManagerImpl {

    public UserManagerRsa() {
        super(() -> List.of(
                ValueRepository.of(UsersRsa.ALL.makeImmutableAndGet())
                // Utils.castOrNull(PersistenceRepository.of(UserPersistable.class), ResourceRepository.class)
        ));
    }
}