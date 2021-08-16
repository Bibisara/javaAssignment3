package kz.aitu;

import kz.aitu.controllers.UserController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final UserController controller;
    private final Scanner scanner;

    public MyApplication(UserController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("Welcome to My Application");
            System.out.println("Select option:");
            System.out.println("1. Log in");
            System.out.println("2. Sign in");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.print("Enter option (1-2): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    LogIn();
                } else if (option == 2) {
                    createUserMenu();
                } else {
                    break;
                }
            } catch (InputMismatchException _) {
                System.out.println("Input must be integer");
                scanner.nextLine(); // to ignore incorrect input
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");

        }
    }

    public void LogIn() {
        System.out.println("Please enter username");
        String username = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();

        while(true){
            String response = controller.getUsername(username, password);
            if (response == "User was not found!") System.out.println(response);
                else if (response == "Password is incorrect!") System.out.println(response);
                    else break;
        }
        while (true){
            System.out.println();
            System.out.println("Select option:");
            System.out.println("1. Search for any medicine by name");
            System.out.println("2. Get medicine by medicine_id");
            System.out.println("3. Add medicine to DB");
            System.out.println("4. Remove medicine by id");
            System.out.println("5. Get expired medicines");
            System.out.println("0. Log out");
            System.out.println();
            try {
                System.out.print("Enter option (1-5): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    SearchMedicineByName(username);
                } else if (option == 2) {
                    getMedicineByIdMenu(username);
                } else if (option == 3) {
                    addMedicineToDB(username);
                } else if (option == 4) {
                    removeMedicineById(username);
                } else if (option == 5) {
                    getExpiredMedicinesMenu(username);
                } else {
                    break;
                }
            } catch (InputMismatchException _) {
                System.out.println("Input must be integer");
                scanner.nextLine(); // to ignore incorrect input
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }
    }

    public void SearchMedicineByName(String username){
        System.out.println("Please enter name of medicine");
        String name = scanner.next();
        String response = controller.searchByName(username, name);
        System.out.println(response);
    }

    public void getMedicineByIdMenu(String username){
        System.out.println("Please enter id of medicine");
        int id = scanner.nextInt();
        String response = controller.searchById(username, id);
        System.out.println(response);
    }

    public void addMedicineToDB(String username) throws ParseException {
        System.out.println("Please enter name of medicine");
        String name = scanner.next();
        System.out.println("Please enter price of medicine");
        int price = scanner.nextInt();
        System.out.println("Please enter expiration date of medicine(in this order: yyyy-MM-dd)");
        String dateString = scanner.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dateString);
        System.out.println("Please enter manufacturer of medicine");
        String manufacturer = scanner.next();
        System.out.println("Please enter type of medicine");
        String type = scanner.next();
        String response = controller.addMedicine(name, price, date, manufacturer, type, username);
        System.out.println(response);
    }

    public void removeMedicineById(String username){
        System.out.println("Please enter id of medicine");
        int id = scanner.nextInt();
        String response = controller.removeMedicine(username, id);
        System.out.println(response);
    }

    public void getExpiredMedicinesMenu(String username) throws ParseException {
        System.out.println("Please enter current date in this order: yyyy-MM-dd");
        String dateString = scanner.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dateString);
        String response = controller.getExpiredMedicines(username, date);
        System.out.println(response);
    }

    public void createUserMenu() {
        System.out.println("Please enter username");
        String username = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();

        String response = controller.createUser(username, password);
        System.out.println(response);
    }
}
