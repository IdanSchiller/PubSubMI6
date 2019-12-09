package bgu.spl.mics;

import bgu.spl.mics.application.passiveObjects.Agent;
import bgu.spl.mics.application.passiveObjects.Squad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class SquadTest {
    private Squad sq;
    @BeforeEach
    public void setUp(){
        sq=new Squad();
        Agent a = new Agent();
        Agent b = new Agent();
        Agent c = new Agent();
        a.setName("a");
        b.setName("b");
        c.setName("c");
        a.setSerialNumber("007");
        b.setSerialNumber("009");
        c.setSerialNumber("0012");
        Agent[] s = {a,b,c};
        sq.load(s);
    }

    @Test
    public void test(){
        //TODO: change this test and add more tests :)
        fail("Not a good test");
    }
}
