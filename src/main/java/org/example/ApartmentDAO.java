package org.example;

import java.util.List;

public interface ApartmentDAO {
    void insert(Apartment apartment);
    List<Apartment> getAll();
    List<Apartment> getByPriceRange(double minPrice, double maxPrice);
    List<Apartment> getByArea(double area);
}
