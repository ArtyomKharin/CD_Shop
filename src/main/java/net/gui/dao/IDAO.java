package net.gui.dao;

import net.gui.models.MusicLabelEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by EvSpirit on 20.12.2016.
 */
public interface IDAO<T> {
    public List<T> getAll();

    public T selectById(int id);

    public void update(T object);

    public T insert(T object);

    public void delete(int id);
    public void error();
}
