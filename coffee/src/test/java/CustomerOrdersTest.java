import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerOrdersTest {

    @Test
    void testTeaNoSugar() {
        CustomerOrders order = new CustomerOrders("T::", 40);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 tea with no sugar - and therefore no stick";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 th� (tea), 0 sucre (sugar) et 0 touillette (stick)");
    }

    @Test
    void testCoffeeNoSugar() {
        CustomerOrders order = new CustomerOrders("C::", 60);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 coffee with no sugar - and therefore no stick";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 caf� (coffee), 0 sucre (sugar) et 0 touillette (stick)");
    }

    @Test
    void testChocolateNoSugar() {
        CustomerOrders order = new CustomerOrders("H::", 50);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 chocolate with no sugar - and therefore no stick";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 chocolat (chocolate), 0 sucre (sugar) et 0 touillette (stick)");
    }

    /*******************************************************************************************************/

    @Test
    void testTeaOneSugar() {
        CustomerOrders order = new CustomerOrders("T:1:0", 100);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 tea with 1 sugar and a stick";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 th� (tea), 1 sucre (sugar) et 1 touillette (stick)");
    }

    @Test
    void testCoffeeOneSugar() {
        CustomerOrders order = new CustomerOrders("C:1:0", 90);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 coffee with 1 sugar and a stick";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 caf� (coffee), 1 sucre (sugar) et 1 touillette (stick)");
    }

    @Test
    void testChocolateOneSugar() {
        CustomerOrders order = new CustomerOrders("H:1:0", 70);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 chocolate with 1 sugar and a stick";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 chocolat (chocolate), 1 sucre (sugar) et 1 touillette (stick)");
    }

    /*******************************************************************************************************/

    @Test
    void testTeaTwoSugar() {
        CustomerOrders order = new CustomerOrders("T:2:0", 120);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 tea with 2 sugars and a stick";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 th� (tea), 2 sucres (sugar) et 1 touillette (stick)");
    }

    @Test
    void testCoffeeTwoSugar() {
        CustomerOrders order = new CustomerOrders("C:2:0", 80);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 coffee with 2 sugars and a stick";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 caf� (coffee), 2 sucres (sugar) et 1 touillette (stick)");
    }

    @Test
    void testChocolateTwoSugar() {
        CustomerOrders order = new CustomerOrders("H:2:0", 110);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 chocolate with 2 sugars and a stick";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 chocolat (chocolate), 2 sucres (sugar) et 1 touillette (stick)");
    }

    /*****************************  Les 3 tests suivants sont pour tester le nombre de cents manquant  ****************************/

    @Test
    void testTeaTwoSugarNotEnoughMoney() {
        CustomerOrders order = new CustomerOrders("T:2:0", 30);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Missing 10 cents";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui dit qu'il manque 10 cents");
    }

    @Test
    void testCoffeeTwoSugarNotEnoughMoney() {
        CustomerOrders order = new CustomerOrders("C:2:0", 40);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Missing 20 cents";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui dit qu'il manque 20 cents");
    }

    @Test
    void testChocolateTwoSugarNotEnoughMoney() {
        CustomerOrders order = new CustomerOrders("H:2:0", 10);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Missing 40 cents";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui dit qu'il manque 40 cents");
    }

    /******************************************************************************************************************************/

    @Test
    void testOrangeJuice() {
        CustomerOrders order = new CustomerOrders("O::", 60);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 orange juice";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 jus d'orange");
    }

    @Test
    void testExtraHotCoffeeNoSugar() {
        CustomerOrders order = new CustomerOrders("Ch::", 60);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 extra hot coffee with no sugar - and therefore no stick";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 caf� extra chaud, 0 sucre et une touillette");
    }

    @Test
    void testExtraHotChocolateOneSugar() {
        CustomerOrders order = new CustomerOrders("Hh:1:0", 50);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 extra hot chocolate with 1 sugar and a stick";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 chocolat extra chaud, 1 sucre et 1 touillette");
    }

    @Test
    void testExtraHotTeaTwoSugar() {
        CustomerOrders order = new CustomerOrders("Th:2:0", 40);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Drink maker makes 1 extra hot tea with 2 sugars and a stick";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui contient 1 th� extra chaud, 2 sucre et 1 toullette");
    }


    /****************************************/

    @Test
    void testOrangeJuiceNotEnoughMoney() {
        CustomerOrders order = new CustomerOrders("O::", 30);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Missing 30 cents";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui dit qu'il manque 30 cents");
    }

    @Test
    void testExtraHotCoffeeNoSugarNotEnoughMoney() {
        CustomerOrders order = new CustomerOrders("Ch::", 40);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Missing 20 cents";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui dit qu'il manque 20 cents");
    }

    @Test
    void testExtraHotChocolateOneSugarNotEnoughMoney() {
        CustomerOrders order = new CustomerOrders("Hh:1:0", 10);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Missing 40 cents";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui dit qu'il manque 40 cents");
    }

    @Test
    void testExtraHotTeaTwoSugarNotEnoughMoney() {
        CustomerOrders order = new CustomerOrders("Th:2:0", 30);
        String actualMessage = order.drinkMakerMessage();
        String expectedMessage = "Missing 10 cents";
        assertEquals(expectedMessage, actualMessage, "Doit retourner un message qui dit qu'il manque 10 cents");
    }


}
