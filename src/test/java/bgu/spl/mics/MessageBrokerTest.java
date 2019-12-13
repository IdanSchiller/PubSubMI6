package bgu.spl.mics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class MessageBrokerTest {

    Event event;// = new MissionRecievedEvent();
    Subscriber simlpeSub;// = new SimpleSubscriber();
    String resault = "Not Resolved";
    Class<MissionRecievedEvent> c ;

    @BeforeEach
    public void setUp(){
        event = new MissionRecievedEvent(resault);
        simlpeSub = new SimpleSubscriber();
    }


    public void testSubscribEvent(){
            MessageBrokerImpl ms = new MessageBrokerImpl();
            ms.subscribeEvent(c,simlpeSub);
    }
    @Test
    public void test(){
        //TODO: change this test and add more tests :)
        fail("Not a good test");
    }
}
