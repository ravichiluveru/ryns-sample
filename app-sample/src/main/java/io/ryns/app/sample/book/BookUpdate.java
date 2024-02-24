package io.ryns.app.sample.book;

import io.ryns.platform.core.authorization.AccessDeniedException;
import io.ryns.platform.core.authorization.AccessResult;
import io.ryns.platform.core.authorization.AuthorizationService;
import io.ryns.platform.core.impl.ioh.ValidationTypes;
import io.ryns.platform.core.ioh.HandlerBinding;
import io.ryns.platform.core.ioh.Input;
import io.ryns.platform.core.ioh.InputType;
import io.ryns.platform.core.ioh.Output;
import io.ryns.platform.core.ioh.SimpleHandler;
import io.ryns.platform.core.persist.PersistenceService;
import io.ryns.platform.core.request.RequestContext;
import io.ryns.platform.core.resource.ResourceParam;
import io.ryns.platform.core.user.User;
import io.ryns.platform.core.util.Optional2;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-14
 */
public interface BookUpdate {
    class In extends BookCU implements Input<Book> {
        public static final InputType<In> TYPE = InputType.of(In.class);

        public final ResourceParam<Book> book = new ResourceParam<>(this, Book.PC_BOOK_BY_ID);

        @Override
        public InputType<In> getInputType() {
            return TYPE;
        }
    }

    @HandlerBinding(value = In.class)
    class Handler implements SimpleHandler<In, Book> {
        @Override
        public void doValidate(RequestContext rc, In in, Output.Buildable<Book> res) {
            if (in.book.get() == null) {
                res.addValidationResponseCode(rc, in, ValidationTypes.Enum.MISSING_REQUIRED_FIELD, "book");
            } else {
                var accessResult = AuthorizationService.get(rc).evaluateResourceAccess(rc, in.book.get());
                if (!AccessResult.isAccessAllowed(accessResult)) {
                    throw new AccessDeniedException();
                }
            }
        }

        @Override
        public void doProcess(RequestContext rc, In in, Output.Buildable<Book> res) {
            BookPersistable persisted = Optional2.ofNullSafeCast(in.book.get(), BookPersistable.class).accept(p -> p.update(in)).map(p -> PersistenceService.saveIt(rc, p)).get();
            if (persisted != null) {
                res.body.set(persisted);
                res.addSuccessResponseCode(rc, in);
            } else {
                res.addFailureResponseCode(rc, in, "Unable to update Book");
            }
        }
    }
}