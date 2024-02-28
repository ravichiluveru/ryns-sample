package io.ryns.app.sample.book;

import io.ryns.platform.core.impl.persist.VersionedMapPersistable;
import io.ryns.platform.core.impl.persist.sql.SqlColumn;
import io.ryns.platform.core.persist.PersistableBinding;
import io.ryns.platform.core.util.ListCollector;
import io.ryns.platform.core.util.Utils;

import java.util.List;
import java.util.Map;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-01
 */
@PersistableBinding(name = BookPersistable.BN, type = BookPersistable.class)
public class BookPersistable extends VersionedMapPersistable implements Book {
    public static final String BN = "book";

    private enum Columns implements Column {
        ownerUserId, name, author, pages,
        /* */;
        private static final ListCollector<SqlColumn> ALL_COLUMNS = new ListCollector<>();
/*
        private static final SqlColumn dbColumn = ALL_COLUMNS.add(SqlColumns.columnName);
*/
    }

    private static final List<String> TABLE_DB_SCRIPTS = List.of(
            // uniqueIndex(BN, SqlColumn.dbColumn);
    );

    @Override
    protected List<String> getTableDbScripts() {
        return TABLE_DB_SCRIPTS;
    }

    public BookPersistable() {
    }

    BookPersistable(final BookCreate.In in) {
        putParam(Columns.name, in.name);
        putParam(Columns.author, in.author);
        putParam(Columns.pages, in.pages);
        putResourceId(Columns.ownerUserId, in.owner);
    }

    boolean update(BookUpdate.In in) {
        putParam(Columns.author, in.author);
        putParam(Columns.pages, in.pages);
        if (!Utils.isNullOrEmpty(in.owner)) {
            putResourceId(Columns.ownerUserId, in.owner);
        }
        return true;
    }

    public BookPersistable(final String id, final int version, final Map<String, Object> initialData) {
        super(id, version, initialData);
    }

    @Override
    public String getOwnerUserId() {
        return get(Columns.ownerUserId);
    }

    @Override
    public String getName() {
        return get(Columns.name);
    }

    @Override
    protected List<Column> getColumns() {
        return (List) Columns.ALL_COLUMNS.makeImmutableAndGet();
    }
}