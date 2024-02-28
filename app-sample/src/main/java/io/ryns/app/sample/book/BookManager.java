package io.ryns.app.sample.book;

import io.ryns.platform.core.request.RequestContext;
import io.ryns.platform.core.resource.ResourceManager;
import io.ryns.platform.core.resource.ResourceManagers;
import io.ryns.platform.core.util.BindingType;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-01
 */
public interface BookManager extends ResourceManager<Book> {
    BindingType<BookManager> BINDING = BindingType.of(BookManager.class);

    static BookManager get(final RequestContext rc) {
        return ResourceManagers.get(rc, BINDING);
    }

    @Override
    default Book.Fields<Book> getFields() {
        return Book.FIELDS;
    }
}