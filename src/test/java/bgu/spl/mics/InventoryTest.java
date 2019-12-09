package bgu.spl.mics;

import bgu.spl.mics.application.passiveObjects.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;

public class InventoryTest {
    @BeforeEach
    public void setUp(){
        Inventory inv = new Inventory();

    }

    //@Test
    public void testGetInstance(){

    }



    @Test
    public void test(){
        testGetInstance();
        //TODO: change this test and add more tests :)
        fail("Not a good test");
    }
}
