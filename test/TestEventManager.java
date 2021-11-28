package test;

import util.EventManager;

import util.Event;

class MessageEvent extends Event {
    private String message;

    public MessageEvent(int date, String message) {
        super(date);
        this.message = message;
    }

    public void execute() {
        System.out.println(this.getDate() + this.message);
    }
}

public class TestEventManager {
    public static void main(String[] args) throws InterruptedException {
        EventManager manager = new EventManager();
        for (int i = 2; i<=10; i+= 2) {
            manager.addEvent(new MessageEvent(i, "PING"));
        }
        for (int i=3; i<=9; i+= 3) {
            manager.addEvent(new MessageEvent(i, "PONG"));
        }

        while (!manager.isFinished()) {
            manager.next();
            Thread.sleep(1000);
        }

    }
}
