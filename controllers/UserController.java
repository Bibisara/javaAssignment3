package kz.aitu.controllers;

import kz.aitu.entities.Medicine;
import kz.aitu.entities.User;
import kz.aitu.repositories.interfaces.IUserRepository;

import java.util.Date;
import java.util.List;

public class UserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    public String createUser(String username, String password) {
        User user = new User(username, password);

        boolean created = repo.createUser(user);

        return (created ? "User was created!" : "User creation was failed!");
    }

    public String getUsername(String username, String password) {
        User user = repo.getUsername(username);

        if (user == null) {
            return "User was not found!";
        }
        if (!password.equals(user.getPassword())) {
            return "Password is incorrect!";
        }
        return null;
    }

    public String searchByName(String username, String name){
        User user = repo.getUsername(username);
        Medicine medicine = repo.searchByName(user.getId(), name);
        return (medicine == null ? "Medicine was not found!" : medicine.toString());
    }

    public String searchById(String username, int id){
        User user = repo.getUsername(username);
        Medicine medicine = repo.searchById(user.getId(), id);
        return (medicine == null ? "Medicine was not found!" : medicine.toString());
    }

    public String addMedicine(String name, int price, Date expiration, String manufacturer, String type, String username){
        User user = repo.getUsername(username);
        Medicine medicine = new Medicine(name, price, expiration, manufacturer, type, user.getId());
        boolean added = repo.addMedicine(medicine);
        return (added ? "Medicine was added!" : "Medicine adding was failed!");
    }

    public String removeMedicine(String username, int id){
        User user = repo.getUsername(username);
        boolean removed = repo.removeMedicine(user.getId(), id);
        return (removed ? "Medicine was removed!" : "Medicine removing was failed!");
    }

    public String getExpiredMedicines(String username, Date date){
        User user = repo.getUsername(username);
        List<Medicine> medicines = repo.getExpiredMedicines(user.getId(), date);
        return (medicines == null ? "Expired medicine was not found!" : medicines.toString());
    }
}


