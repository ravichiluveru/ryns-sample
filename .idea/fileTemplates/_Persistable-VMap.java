#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import java.util.List;
import java.util.Map;

import io.ryns.platform.core.impl.persist.VersionedMapPersistable;
import io.ryns.platform.core.impl.persist.sql.SqlColumn;
import io.ryns.platform.core.persist.PersistableBinding;
import io.ryns.platform.core.util.ListCollector;

#parse("File Header.java")

@PersistableBinding(name = ${ResourceName}Persistable.BN, type = ${ResourceName}Persistable.class)
public class ${ResourceName}Persistable extends VersionedMapPersistable implements ${ResourceName} {
    public static final String BN = SqlTables${AppCode}.${ResourceName}; //TODO verify / fix and remove todo
    private enum Columns
            implements Column {
        /* */;
        private static final ListCollector<SqlColumn> ALL_COLUMNS = new ListCollector<>();
/*
        private static final SqlColumn dbColumn = ALL_COLUMNS.add(SqlColumns${AppCode}.columnName);
*/
    }

    private static final List<String> TABLE_DB_SCRIPTS = List.of(
        // uniqueIndex(BN, SqlColumn.dbColumn);
    );

    @Override
    protected List<String> getTableDbScripts() {
        return TABLE_DB_SCRIPTS;
    }

    public ${ResourceName}Persistable() {
    }

    public ${ResourceName}Persistable(final String id, final int version, final Map<String, Object> initialData) {
        super(id, version, initialData);
    }
    @Override
    protected List<Column> getColumns() {
        return (List) Columns.ALL_COLUMNS.makeImmutableAndGet();
    }
}