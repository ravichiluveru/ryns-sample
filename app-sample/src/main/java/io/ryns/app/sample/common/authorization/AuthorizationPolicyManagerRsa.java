package io.ryns.app.sample.common.authorization;

import java.util.List;

import io.ryns.platform.core.authorization.AuthorizationPolicyManager;
import io.ryns.platform.core.impl.authorization.AuthorizationPolicyManagerImpl;
import io.ryns.platform.core.repository.ValueRepository;
import io.ryns.platform.core.resource.ResourceManagerBinding;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-14
 */
@ResourceManagerBinding(value = AuthorizationPolicyManager.class)
public class AuthorizationPolicyManagerRsa extends AuthorizationPolicyManagerImpl {

    public AuthorizationPolicyManagerRsa() {
        super(() -> List.of(
                 ValueRepository.of(AuthorizationPoliciesRsa.ALL.makeImmutableAndGet())
                // Utils.castOrNull(PersistenceRepository.of(AuthorizationPolicyPersistable.class), ResourceRepository.class)
        ));
    }
}