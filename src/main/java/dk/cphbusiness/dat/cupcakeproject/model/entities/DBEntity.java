package dk.cphbusiness.dat.cupcakeproject.model.entities;

public class DBEntity <T>
{
    private final int id;
    private final T object;
    private Boolean isDeleted;

    public DBEntity(int id, T object)
    {
        this.id = id;
        this.object = object;
        this.isDeleted = null;
    }

    public int getId()
    {
        return id;
    }

    public T getObject()
    {
        return object;
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
