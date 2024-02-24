package io.ryns.app.sample.book;

import io.ryns.platform.core.buildable.ParamConfig;
import io.ryns.platform.core.query.FilterOps;
import io.ryns.platform.core.query.QueryField;
import io.ryns.platform.core.query.QueryFieldBuilder;
import io.ryns.platform.core.resource.Resource;
import io.ryns.platform.core.resource.template.ResourceWithName;
import io.ryns.platform.core.util.Strings;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-01
 */
public interface Book extends Resource, ResourceWithName {

    Fields.I FIELDS = new Fields.I();

    String getOwnerUserId();

    interface Fields<Q extends Book>
            extends Resource.Fields<Q>, ResourceWithName.Fields<Q> {

        default QueryField<Q> ownerUserId() {
            return field(FIELDS.OWNER_USER_ID);
        }

        class I implements Fields<Book> {
            private final QueryField<Book> OWNER_USER_ID = new QueryFieldBuilder<Book>()
                    .setName("ownerUserId")
                    .setQueryableClass(Book.class)
                    .setSupportedOps(FilterOps.EQUAL, FilterOps.IN)
                    .setValueFunction((c, q) -> q != null ? q.getOwnerUserId() : null)
                    .build();

            private I() {
            }
        }
    }

    ParamConfig<Book> PC_BOOK_BY_ID = new ParamConfig.Builder<Book>()
            .setBindHint(ParamConfig.Hint.Bind.HTTP_URL_PATH)
            .setTypeClass(Book.class)
            .setParser((rc, o) -> BookManager.get(rc).getResource(rc, Strings.valueOrNull(o)))
            .build();
}