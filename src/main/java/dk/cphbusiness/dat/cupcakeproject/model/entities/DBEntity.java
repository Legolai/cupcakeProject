package dk.cphbusiness.dat.cupcakeproject.model.entities;

public class DBEntity <T>
{
    private final int id;
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

    public void setDeleted(Boolean deleted)
    {
        isDeleted = deleted;
    }
}
