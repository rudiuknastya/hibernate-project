package hibernateDao;

import java.util.List;

public interface Dao<T> {
    public void addNewElement(T element);
    public List<T> getAllElements();
    public T getElementById(long id);
    public void updateElement(T element);
    public void deleteElement(long id);
}
