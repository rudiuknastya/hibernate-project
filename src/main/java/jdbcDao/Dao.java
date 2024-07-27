package jdbcDao;

import java.util.List;

public interface Dao<T> {
    public void addNewElement(T element);
    public List<T> getAllElements();
    public T getElementById(Long id);
    public void updateElement(T element);
    public void deleteElement(Long id);
}
