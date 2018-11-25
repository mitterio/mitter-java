package io.mitter.data.domain.pagination;

/**
 * Common criteria for pagination.
 *
 * @author Ganessh Kumar(ganessh@nomadly.in)
 */
public class PaginationCriteria<T> {
    /**
     * Number of objects to be retrieved
     */
    private int size;

    /**
     * Indicating whether next or previous items to be retrieved from the since token
     */
    private PaginationType paginationType;

    private T since;

    public int getSize() {
        return size;
    }

    public PaginationCriteria setSize(int size) {
        this.size = size;
        return this;
    }

    public PaginationType getPaginationType() {
        return paginationType;
    }

    public PaginationCriteria setPaginationType(PaginationType paginationType) {
        this.paginationType = paginationType;
        return this;
    }

    public T getSince() {
        return since;
    }

    public PaginationCriteria setSince(T since) {
        this.since = since;
        return this;
    }
}
