package kafka;

import kafka.Maker.ConsumerSequential;

public class MainApp {
    public static void main(String[] args) {

        System.out.println("--");

        MainApp ma = new MainApp();
        ma.start();
    }

    public void start() {

        Consumer con = new Consumer();
        ConsumerSequential con = new ConsumerSequential();
        con.start();
    }
}
