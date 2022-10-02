package ku.cs.models.report;

public interface Filterer<T> {
    boolean filter(T t);
}
