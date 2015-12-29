package com.epam.alex.task4.entity;

/**
 * Abstract entity contains id and used to inheritance another entities
 * Created by AlexTuli on 12/6/15.
 *
 * @author Bocharnikov Alexandr
 */
public abstract class AbstractEntity {

    private int id;

    public AbstractEntity(int id) {
        this.id = id;
    }

    public AbstractEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ID = " + id;
    }
}
