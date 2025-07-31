package org.example;

import java.util.Objects;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApartamentDAO dao = new ApartamentDAO();
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("---------------------");
                System.out.println("1: add flat");
                System.out.println("2: view apartments");
                System.out.println("3: filters by price ? between ?");
                System.out.println("4: filters by min area");
                System.out.println("---------------------");
                String q = sc.nextLine();

                switch (q) {
                    case "1":
                        System.out.println("Enter district: ");
                        String district = sc.nextLine();

                        System.out.println("Enter address: ");
                        String address = sc.nextLine();

                        System.out.println("Enter area: ");
                        double area = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("Enter rooms: ");
                        int rooms = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Enter price: ");
                        double price = sc.nextDouble();
                        sc.nextLine();

                        Apartment app = new Apartment(district, address, area, rooms, price);
                        dao.insert(app);
                        System.out.println(app.toString());
                        break;

                    case "2":
                        for (Apartment ap : dao.getAll()) {
                            System.out.println(ap.toString());
                        }
                        break;

                    case "3":
                        System.out.print("Enter min price: ");
                        double minPrice = sc.nextDouble();
                        sc.nextLine();

                        System.out.print("Enter max price: ");
                        double maxPrice = sc.nextDouble();
                        sc.nextLine();

                        for (Apartment ap : dao.getByPriceRange(minPrice, maxPrice)) {
                            System.out.println(ap.toString());
                        }
                        break;

                    case "4":
                        System.out.print("Enter min area: ");
                        double minArea = sc.nextDouble();
                        sc.nextLine();

                        for (Apartment ap:  dao.getByArea(minArea)) {
                            System.out.println(ap.toString());
                        }
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
    }
}
