package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartamentDAO extends AbstractDAO{
    public void insert(Apartment apartment) {
        String sql = "INSERT INTO apartments (district, address, area, rooms, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setString(1, apartment.getDistrict());
            stmt.setString(2, apartment.getAddress());
            stmt.setDouble(3, apartment.getArea());
            stmt.setDouble(4, apartment.getRooms());
            stmt.setDouble(5, apartment.getPrice());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }

    public List<Apartment> getAll() {
        List<Apartment> result = new ArrayList<>();
        String sql = "SELECT * FROM apartments";

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql);){
            while (rs.next()) {
                result.add(appart(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();;
        }
        return result;
    }

    public List<Apartment> getByPriceRange(double minPrice, double maxPrice) {
        List<Apartment> result = new ArrayList<>();
        String sql = "SELECT * FROM apartments WHERE price BETWEEN ? AND ? ";

        try (Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(appart(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();;
        }
        return result;
    }


    public List<Apartment> getByArea(double area) {
        List<Apartment> result = new ArrayList<>();
        String sql = "SELECT * FROM apartments WHERE area >= ?";

        try (Connection con = getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDouble(1, area);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(appart(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();;
        }
        return result;
    }

    private Apartment appart(ResultSet rs) throws SQLException {
        return new Apartment(
                rs.getInt("id"),
                rs.getString("district"),
                rs.getString("address"),
                rs.getDouble("area"),
                rs.getInt("rooms"),
                rs.getDouble("price")
        );
    }
}
