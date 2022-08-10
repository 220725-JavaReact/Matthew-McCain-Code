package dao.storage;

import dao.DAO;
import model.*;
import model.CATEGORIES;
import services.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostgresqlDAO implements DAO {

    @Override
    public void addCustomer(Customer customer) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "insert into customers (name, address, email, phone) values (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, customer.getName());
            statement.setInt(2, addAddress(customer.getAddress()));
            statement.setString(3, customer.getEmail());
            statement.setInt(4, Integer.parseInt(customer.getPhone()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findCustomer(String name) throws Exception {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from customers where name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getString("name"), getAddress(rs.getInt("address")),
                        rs.getString("email"), Integer.toString(rs.getInt("phone")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "insert into products (name, price, category, description) values (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getCategory().toString());
            statement.setString(4, product.getDescription());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findProduct(String name) throws Exception {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from product where name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Product(rs.getString("name"), CATEGORIES.valueOf(rs.getString("category")),
                        rs.getDouble("price"), rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addStoreFront(StoreFront store) {
        System.out.println("PostgresqlDAO received store: " + store.getName());
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "insert into store_fronts (name, address, phone) values (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, store.getName());
            statement.setInt(2, addAddress(store.getAddress()));
            statement.setInt(3, Integer.parseInt(store.getPhone()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<StoreFront> getStores() {
        ArrayList<StoreFront> storeFronts = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from store_fronts";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // StoreFront(String name, Address address, String phone)
                storeFronts.add(new StoreFront(rs.getString("name"),
                        getAddress(rs.getInt("address")), Integer.toString(rs.getInt("phone"))));
            }
            return storeFronts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int addAddress(Address address) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            // Insert address
            String query = "insert into addresses (building, street, city, state, zip) values (?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, address.getBuildingNumber());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getState());
            statement.setInt(5, address.getZip());
            statement.execute();

            // return inserted address
            query = "select id from addresses where building = ? " +
                    "and street = ? " +
                    "and city = ? " +
                    "and state = ? " +
                    "and zip = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, address.getBuildingNumber());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getState());
            statement.setInt(5, address.getZip());
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
            else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Address getAddress(int id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from addresses where id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Address(rs.getInt("building"), rs.getString("street"),
                        rs.getString("city"), rs.getString("state"), rs.getInt("zip"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
