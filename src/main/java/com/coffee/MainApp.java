package com.coffee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(DrinkCategory.class)
                .addAnnotatedClass(CoffeeOrder.class)
                .addAnnotatedClass(MenuItem.class)
                .addAnnotatedClass(Sale.class)
                .buildSessionFactory();

        // Первая транзакция: заполнение базы данными из Практической работы №2
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            // 1. Категории напитков
            DrinkCategory catMilk = new DrinkCategory("Кава з молоком", "Керамічна чашка");
            DrinkCategory catTea = new DrinkCategory("Трав'яний чай", "Чайна чашка");
            DrinkCategory catBlack = new DrinkCategory("Чорна кава", "Мала чашка");

            session.save(catMilk);
            session.save(catTea);
            session.save(catBlack);

            // 2. Заказы
            CoffeeOrder o2101 = new CoffeeOrder(2101, LocalDate.of(2026, 9, 2), "На Соборній");
            CoffeeOrder o2102 = new CoffeeOrder(2102, LocalDate.of(2026, 9, 2), "Біля парку");
            CoffeeOrder o2103 = new CoffeeOrder(2103, LocalDate.of(2026, 9, 3), "На Соборній");
            CoffeeOrder o2104 = new CoffeeOrder(2104, LocalDate.of(2026, 9, 4), "Біля парку");
            CoffeeOrder o2105 = new CoffeeOrder(2105, LocalDate.of(2026, 9, 4), "На Соборній");
            CoffeeOrder o2106 = new CoffeeOrder(2106, LocalDate.of(2026, 9, 14), "На Соборній");
            CoffeeOrder o2107 = new CoffeeOrder(2107, LocalDate.of(2026, 9, 24), "Біля парку");
            CoffeeOrder o2108 = new CoffeeOrder(2108, LocalDate.of(2026, 10, 4), "На Соборній");
            CoffeeOrder o2109 = new CoffeeOrder(2109, LocalDate.of(2026, 10, 14), "Біля парку");
            CoffeeOrder o2110 = new CoffeeOrder(2110, LocalDate.of(2026, 10, 24), "На Соборній");
            CoffeeOrder o2111 = new CoffeeOrder(2111, LocalDate.of(2026, 11, 3), "На Соборній");
            CoffeeOrder o2112 = new CoffeeOrder(2112, LocalDate.of(2026, 11, 13), "Біля парку");

            session.save(o2101);
            session.save(o2102);
            session.save(o2103);
            session.save(o2104);
            session.save(o2105);
            session.save(o2106);
            session.save(o2107);
            session.save(o2108);
            session.save(o2109);
            session.save(o2110);
            session.save(o2111);
            session.save(o2112);

            // 3. Меню
            MenuItem mCap = new MenuItem("Капучино 250", catMilk, 250);
            MenuItem mLat = new MenuItem("Лате 350", catMilk, 350);
            MenuItem mTea = new MenuItem("Чай м'ятний 300", catTea, 300);
            MenuItem mEsp = new MenuItem("Еспресо 30", catBlack, 30);

            session.save(mCap);
            session.save(mLat);
            session.save(mTea);
            session.save(mEsp);

            // 4. Продажи
            Sale[] sales = {
                new Sale(o2101, mCap, 2, 65), new Sale(o2101, mLat, 1, 75), new Sale(o2101, mTea, 1, 70),
                new Sale(o2102, mLat, 1, 70), new Sale(o2102, mEsp, 2, 42),
                new Sale(o2103, mCap, 3, 65), new Sale(o2103, mTea, 1, 50), new Sale(o2103, mLat, 2, 42),
                new Sale(o2104, mEsp, 2, 78), new Sale(o2104, mTea, 1, 52),
                new Sale(o2105, mCap, 1, 45), new Sale(o2105, mEsp, 2, 50),
                new Sale(o2106, mCap, 2, 65), new Sale(o2106, mLat, 1, 75), new Sale(o2106, mTea, 1, 70),
                new Sale(o2107, mLat, 1, 70), new Sale(o2107, mEsp, 2, 42),
                new Sale(o2108, mCap, 3, 65), new Sale(o2108, mTea, 1, 50), new Sale(o2108, mLat, 2, 42),
                new Sale(o2109, mEsp, 2, 78), new Sale(o2109, mTea, 1, 52),
                new Sale(o2110, mCap, 1, 45), new Sale(o2110, mEsp, 2, 50),
                new Sale(o2111, mCap, 2, 65), new Sale(o2111, mLat, 1, 75), new Sale(o2111, mTea, 1, 70),
                new Sale(o2112, mLat, 1, 70), new Sale(o2112, mEsp, 2, 42)
            };

            for (Sale s : sales) {
                session.save(s);
            }

            session.getTransaction().commit();
            System.out.println("Дані успішно збережено в базу coffee_shop!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Вторая транзакция: выборка строк в первой нормальной форме (1НФ)
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            String hql = "SELECT new com.coffee.CoffeeSaleDTO(" +
                    "o.receiptNumber, o.orderDate, o.cafe, " +
                    "m.menuItemName, m.volume, d.category, d.vessel, " +
                    "s.quantity, s.price) " +
                    "FROM Sale s " +
                    "JOIN s.coffeeOrder o " +
                    "JOIN s.menuItem m " +
                    "JOIN m.drinkCategory d " +
                    "ORDER BY o.receiptNumber";

            List<CoffeeSaleDTO> results = session.createQuery(hql, CoffeeSaleDTO.class).getResultList();

            System.out.println("\n=== Таблиця в першій нормальній формі (1НФ) ===");
            for (CoffeeSaleDTO row : results) {
                System.out.println("Чек №: " + row.getReceiptNumber() +
                        " | Дата: " + row.getOrderDate() +
                        " | Кав'ярня: " + row.getCafe() +
                        " | Позиція: " + row.getMenuItemName() +
                        " | Об'єм: " + row.getVolume() + " мл" +
                        " | Категорія: " + row.getCategory() +
                        " | Посуд: " + row.getVessel() +
                        " | Кількість: " + row.getQuantity() +
                        " | Ціна: " + row.getPrice() + " грн");
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}