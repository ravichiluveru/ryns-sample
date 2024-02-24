#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

import io.ryns.platform.core.repository.MultiRepositoryResourceManager;
import io.ryns.platform.core.repository.ResourceRepository;
import io.ryns.platform.core.resource.ResourceManagerBinding;
import io.ryns.platform.core.util.Optional2;
import io.ryns.platform.core.util.SupplyOnce;

import static io.ryns.platform.core.service.Service.DEFAULT;

#parse("File Header.java")

@ResourceManagerBinding(service = DEFAULT, value = ${ResourceName}Manager.class)
public class ${ResourceName}ManagerImpl implements ${ResourceName}Manager, MultiRepositoryResourceManager<${ResourceName}> {

    private final SupplyOnce<Collection<ResourceRepository<${ResourceName}>>> repositories;

    public ${ResourceName}ManagerImpl() {
        this(null);
    }
    protected ${ResourceName}ManagerImpl(final Supplier<Collection<ResourceRepository<${ResourceName}>>> supplier) {
        this.repositories = SupplyOnce.withSupplier(() ->
            Optional2.ofNullSafe(new ArrayList<ResourceRepository<${ResourceName}>>())
                .accept(l -> l.addAll(supplier.get()))
                //.accept(${ResourceName}s.ALL.makeImmutableAndGet(), ValueRepository::addTo)
                //.accept(l -> l.add( Utils.castOrNull(PersistenceRepository.of(${ResourceName}Persistable.class), ResourceRepository.class)  ))
                .map(Collections::unmodifiableList)
                .get()
        );
    }    

    @Override
    public Collection<ResourceRepository<${ResourceName}>> getRepositories() {
        return this.repositories.get();
    }
}