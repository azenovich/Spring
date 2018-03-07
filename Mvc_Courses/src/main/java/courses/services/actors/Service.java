package courses.services.actors;

import java.util.List;

public interface Service<T, TKey> {
    List<T> findAll();
    T findById(TKey id);
    T create(T model);
    T edit(T model);
    void deleteById(TKey id);
}
