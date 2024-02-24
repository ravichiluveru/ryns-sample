package io.ryns.app.sample.book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

import io.ryns.platform.core.persist.PersistenceRepository;
import io.ryns.platform.core.repository.MultiRepositoryResourceManager;
import io.ryns.platform.core.repository.ResourceRepository;
import io.ryns.platform.core.resource.ResourceManagerBinding;
import io.ryns.platform.core.util.Optional2;
import io.ryns.platform.core.util.SupplyOnce;
import io.ryns.platform.core.util.Utils;

import static io.ryns.platform.core.service.Service.DEFAULT;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-16
 */
@ResourceManagerBinding(service = DEFAULT, value = BookManager.class)
public class BookManagerImpl implements BookManager, MultiRepositoryResourceManager<Book> {

    private final SupplyOnce<Collection<ResourceRepository<Book>>> repositories;

    public BookManagerImpl() {
        this(null);
    }

    protected BookManagerImpl(final Supplier<Collection<ResourceRepository<Book>>> supplier) {
        this.repositories = SupplyOnce.withSupplier(() ->
                Optional2.ofNullSafe(new ArrayList<ResourceRepository<Book>>())
                        .accept(l -> l.addAll(supplier.get()))
                        //.accept(Books.ALL.makeImmutableAndGet(), ValueRepository::addTo)
                        .accept(l -> l.add( Utils.castOrNull(PersistenceRepository.of(BookPersistable.class), ResourceRepository.class)  ))
                        .map(Collections::unmodifiableList)
                        .get()
        );
    }

    @Override
    public Collection<ResourceRepository<Book>> getRepositories() {
        return this.repositories.get();
    }
}