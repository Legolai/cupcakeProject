package dk.cphbusiness.dat.cupcakeproject.model.entities;

import java.util.Objects;

public class DBEntity <T>
{
    private int id;
    private final T entity;
    private Boolean isDeleted;

    public DBEntity(int id, T entity)
    {
        this.id = id;
        this.entity = entity;
        this.isDeleted = null;
    }

    public int getId()
    {
        return id;
    }
    public T getEntity()
    {
        return entity;
    }
    public Boolean getDeleted()
    {
        return isDeleted;
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setDeleted(Boolean deleted)
    {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof DBEntity)) return false;
        DBEntity<?> dbEntity = (DBEntity<?>) o;
        return getId() == dbEntity.getId() && getEntity().equals(dbEntity.getEntity());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getEntity());
    }
}
