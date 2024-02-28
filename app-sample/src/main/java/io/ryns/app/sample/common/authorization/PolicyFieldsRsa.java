package io.ryns.app.sample.common.authorization;

import io.ryns.app.sample.book.Book;
import io.ryns.app.sample.common.resource.ResourceTypesRsa;
import io.ryns.platform.core.authorization.AuthorizationPolicyParam;
import io.ryns.platform.core.impl.policy.PolicyFieldImpl;
import io.ryns.platform.core.impl.policy.PolicyOps;
import io.ryns.platform.core.loader.LoadOnStartup;
import io.ryns.platform.core.policy.PolicyField;
import io.ryns.platform.core.resource.ResourceType;
import io.ryns.platform.core.util.ListCollector;
import io.ryns.platform.core.util.Optional2;
import io.ryns.platform.core.util.Utils;

import java.util.List;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-14
 */
@LoadOnStartup
public interface PolicyFieldsRsa {

    ResourceType RT = ResourceTypesRsa.AUTHORIZATION_POLICY_FIELDS_RSA;
    ListCollector<PolicyField<AuthorizationPolicyParam>> ALL = new ListCollector<>();

    PolicyField<AuthorizationPolicyParam>
            BOOK_OWNER_ID = ALL.add(PolicyFieldImpl.<AuthorizationPolicyParam>builder(RT, "1")
            .setName("bookOwnerId")
            .setQueryableClass(AuthorizationPolicyParam.class)
            .setSupportedOps(List.of(PolicyOps.EQUAL,
                    PolicyOps.NOT_EQUAL,
                    PolicyOps.NOT_NULL,
                    PolicyOps.EQUAL_OR_NULL))
            .setValueFunction((c, p) -> Optional2.ofNullSafe(p)
                    .map(AuthorizationPolicyParam::getResource)
                    .map(r -> Utils.castOrNull(r, Book.class))
                    .map(Book::getOwnerUserId)
                    .get())
            .build());
//Sun Feb 18 00:28:31 PST 2024 [INFO] rsa-rl rsa|0218e15c1|h4,/sample/book-create,200,15,131,101.1.3.1$1,,1.5.4.1$604e7142-4886-4da5-9979-85dfd21e52ad,P,1,1,0,0,1.6.5$2,1.6.1$2
    static void init() {
        //Intentional empty method for loading this class during startup with above @LoadOnStartup annotation.
    }
}