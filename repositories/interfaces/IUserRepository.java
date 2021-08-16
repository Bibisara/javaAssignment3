package kz.aitu.repositories.interfaces;

import kz.aitu.entities.Medicine;
import kz.aitu.entities.User;

import java.util.Date;
import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUsername(String username);
    Medicine searchByName(int userId, String name);
    Medicine searchById(int userId, int id);
    boolean addMedicine(Medicine medicine);
    boolean removeMedicine(int userId, int id);
    List<Medicine> getExpiredMedicines(int userId, Date date);
}
