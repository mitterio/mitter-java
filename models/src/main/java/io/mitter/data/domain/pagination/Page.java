package io.mitter.data.domain.pagination;

import java.util.List;

/**
 * Object to hold paginated objects along with metadata.
 * T - Type of the object being paginated
 *
 * @author Ganessh Kumar(ganessh@nomadly.in)
 */
// [TODO] Check the feasibility of including next, first, previous and last PaginationCriteria. Also an interface.

public class Page<T, K> {
    /**
     * Number of objects in this response
     */
    private int size;

    /**
     * Total number of objects in the repository
     */
    private int total;

    /**
     * List of paginated objects
     */
    private List<T> objects;

    /**
     * Public domainId of the first item in the pagination. Used for retrieving previous set of objects
     */
    private K first;

    /**
     * Public domainId of the last item in the pagination. Used for retrieving next set of objects
     */
    private K last;

    public Page() {

    }

    public Page(List<T> objects) {
        this.objects = objects;
        this.size = objects.size();
    }

    public int getSize() {
        return size;
    }

    public Page setSize(int size) {
        this.size = size;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public Page setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<T> getObjects() {
        return objects;
    }

    public Page setObjects(List<T> objects) {
        this.objects = objects;
        return this;
    }

    public K getFirst() {
        return first;
    }

    public Page setFirst(K first) {
        this.first = first;
        return this;
    }

    public K getLast() {
        return last;
    }

    public Page setLast(K last) {
        this.last = last;
        return this;
    }
}
