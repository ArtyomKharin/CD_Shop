package net.gui.services;

import java.util.List;

/**
 * Created by EvSpirit on 20.12.2016.
 */
public interface IService<T>{
    public List<T> getAll();

    public T selectById(int id);

    public void update(T object);

    public T insert(T object);

    public void delete(int id);
}
