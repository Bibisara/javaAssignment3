package kz.aitu.repositories;

import kz.aitu.data.interfaces.IDB;
import kz.aitu.entities.Medicine;
import kz.aitu.entities.User;
import kz.aitu.repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements IUserRepository{
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO users(username, password) VALUES (?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());

            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public User getUsername(String username) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,username,password FROM users WHERE username=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"));
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Medicine searchByName(int userId, String name){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT medicine.id, medicine.name, medicine.price, medicine.expiration_date, medicine.manufacturer, medicine.type_of_medicine, medicine.user_id FROM medicine FULL OUTER JOIN users ON users.id = ? WHERE name = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, userId);
            st.setString(2, name);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Medicine medicine = new Medicine(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getDate("expiration_date"),
                        rs.getString("manufacturer"),
                        rs.getString("type_of_medicine"),
                        rs.getInt("user_id"));
                return medicine;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Medicine searchById(int userId, int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT medicine.id, medicine.name, medicine.price, medicine.expiration_date, medicine.manufacturer, medicine.type_of_medicine, medicine.user_id FROM medicine FULL OUTER JOIN users ON users.id = ? WHERE medicine.id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, userId);
            st.setInt(2, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Medicine medicine = new Medicine(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getDate("expiration_date"),
                        rs.getString("manufacturer"),
                        rs.getString("type_of_medicine"),
                        rs.getInt("user_id"));
                return medicine;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean addMedicine(Medicine medicine) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO medicine(name, price, expiration_date, manufacturer, type_of_medicine, user_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, medicine.getName());
            st.setInt(2, medicine.getPrice());
            st.setDate(3, medicine.getExpiration());
            st.setString(4, medicine.getManufacturer());
            st.setString(5, medicine.getType());
            st.setInt(6, medicine.getUserId());

            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean removeMedicine(int userId, int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM medicine WHERE id = ? AND user_id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);
            st.setInt(2, userId);

            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Medicine> getExpiredMedicines(int userId, Date date){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM medicine WHERE user_id = ? AND expiration_date < ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, userId);
            st.setDate(2, (java.sql.Date) date);
            ResultSet rs = st.executeQuery(sql);
            List<Medicine> medicines = new LinkedList<>();
            while (rs.next()) {
                Medicine medicine = new Medicine(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getDate("expiration_date"),
                        rs.getString("manufacturer"),
                        rs.getString("type_of_medicine"),
                        rs.getInt("user_id"));

                medicines.add(medicine);
            }

            return medicines;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
