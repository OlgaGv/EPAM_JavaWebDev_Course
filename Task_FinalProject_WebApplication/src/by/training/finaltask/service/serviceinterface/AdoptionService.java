package by.training.finaltask.service.serviceinterface;

import by.training.finaltask.entity.Adoption;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface AdoptionService extends Service{

    Adoption get(int ID) throws PersistentException;

    List<Adoption> getAll(int offset, int rowcount) throws PersistentException;

    Integer add(Adoption adoption) throws PersistentException;

    void update(Adoption adoption) throws PersistentException;

    void delete(int ID) throws PersistentException;

}
