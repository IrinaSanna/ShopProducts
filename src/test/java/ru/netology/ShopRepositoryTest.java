package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShopRepositoryTest {

    Product product1 = new Product(1, "Мяч", 1_500);
    Product product2 = new Product(2, "Ракетка", 5_000);
    Product product3 = new Product(3, "Спортивный костюм", 12_000);
    Product product4 = new Product(4, "Набор теннисных мячей", 900);
    Product product5 = new Product(5, "Велосипед", 50_000);
    Product product6 = new Product(3, "Новый товар", 10_000);

    @Test
    public void shouldElementRemovedById() { // удаление товара
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);
        repo.add(product5);

        repo.removeById(3);

        Product[] expected = {product1, product2, product4, product5};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldErrorWhenDeletingMissingElement() { // удаление несуществующего элемента
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);
        repo.add(product5);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(6);
        });
    }

    @Test
    public void shouldAddProductIfIdNotRepeated() { // добавление товара с id как у удаленного товара
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product4);
        repo.add(product5);

        repo.add(product6);

        Product[] expected = {product1, product2, product4, product5, product6};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddProductWithRepeatingId() { // добавление товара с одинаковыми id
        ShopRepository repo = new ShopRepository();
        repo.add(product3);
        repo.add(product5);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product6);
        });
    }
}