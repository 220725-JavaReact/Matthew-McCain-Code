package dao;

import model.*;
import model.CATEGORIES;
import services.Client;
import services.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostgresqlDAO implements DAO {

    public int createOrder(int storeID, int customerID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "insert into orders (store_id, customer_id) values (?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, storeID);
            statement.setInt(2, customerID);
            statement.execute();
            return getOrderID(storeID, customerID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getOrderID(int storeID, int customerID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "select id from orders where store_id = ? and customer_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, storeID);
            statement.setInt(2, customerID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

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

    public int getCustomerID(Customer customer) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "select id from customers where name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, customer.getName());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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
        //System.out.println("PostgresqlDAO received store: " + store.getName());
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

    @Override
    public ArrayList<ArrayList<String>> getInventory(StoreFront store) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query =
                    "select products.\"category\" , products.\"name\", inventory.quantity \n" +
                    "from products\n" +
                    "inner join inventory\n" +
                    "on products.id = inventory.product_id \n" +
                    "where inventory.store_id = ?\n" +
                    "order by products.category";
            PreparedStatement statement = conn.prepareStatement(query);
            int storeID = getStoreID(store);
            statement.setInt(1, storeID);
            ResultSet rs = statement.executeQuery();
            ArrayList<ArrayList<String>> inventory = new ArrayList<>();
            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(rs.getString("category"));
                row.add(rs.getString("name"));
                row.add(Integer.toString(rs.getInt("quantity")));
                inventory.add(row);
            }
            return inventory;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void adjustInventory(StoreFront store, Product product, int adjustment) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from inventory where store_id = ? and product_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            int storeID = getStoreID(store);
            statement.setInt(1, storeID);
            int productID = getProductID(product);
            statement.setInt(2, productID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                updateInventory(rs.getInt("store_id"), rs.getInt("product_id"), rs.getInt("quantity") + adjustment);
            } else if (adjustment > 0){
                insertInventory(storeID, productID, adjustment);
            } else {
                System.out.println("Initial quantity must be greater than 0.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertInventory(int storeID, int productID, int quantity) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "insert into inventory values (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, storeID);
            statement.setInt(2, productID);
            statement.setInt(3, quantity);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateInventory(int storeID, int productID, int quantity) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "update inventory set quantity = ? where store_id = ? and product_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, storeID);
            statement.setInt(2, productID);
            statement.setInt(3, quantity);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(int orderID, double adjustment) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "update orders set total = ? where id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDouble(1, (getCurrentOrderTotal(orderID) + adjustment));
            statement.setInt(2, orderID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double getCurrentOrderTotal(int orderID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "select total from orders where id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, orderID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<ArrayList<String>> getOrders() {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query =
                    "select customers.name, orders.total \n" +
                            "from orders\n" +
                            "inner join customers\n" +
                            "on orders.customer_id = customers.id";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            ArrayList<ArrayList<String>> inventory = new ArrayList<>();
            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(rs.getString("name"));
                row.add(String.valueOf(rs.getInt("total")));
                inventory.add(row);
            }
            return inventory;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getStoreID(StoreFront store) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "select id from store_fronts where name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, store.getName());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void addLineItem(int orderID, Product product, int quantity) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "insert into orders values (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, orderID);
            statement.setInt(2, getProductID(product));
            statement.setInt(3, quantity);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getProductID(Product product) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "select id from products where name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, product.getName());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public ArrayList<Product> getProducts(CATEGORIES category) {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from products where category = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, category.toString());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getString("name"), Client.getCategory(rs.getString("category")),
                        rs.getDouble("price"), rs.getString("description")));
            }
        } catch (SQLException e) {
            System.out.println("Could not retrieve any products from PostgresqlDAO.");
            e.printStackTrace();
        }
        return products;
    }
}
