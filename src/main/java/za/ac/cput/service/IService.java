package za.ac.cput.sevice;

import org.springframework.stereotype.Service;

@Service
public interface IService<T, ID> {
    T create(T t);
    T read(ID id);
    T update(T t);
    void delete(ID id);
    Iterable<T> getAll();
}