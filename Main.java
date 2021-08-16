package kz.aitu;

import kz.aitu.controllers.UserController;
import kz.aitu.data.DB;
import kz.aitu.data.interfaces.IDB;
import kz.aitu.repositories.UserRepository;
import kz.aitu.repositories.interfaces.IUserRepository;

public class Main {

    public static void main(String[] args) {
        IDB db = new DB();
        IUserRepository repo = new UserRepository(db);
        UserController controller = new UserController(repo);
        MyApplication app = new MyApplication(controller);
        app.start();
    }
}
