package br.com.teste;

import java.lang.Thread.State;


public class PrintApp {
    public static void main( String args[] ) {
        Queue queue = new CircularQueue( 15 );

        Printer printer = new Printer( "Printer", queue );
        Thread printerThread = new Thread( printer );
        printerThread.start();

        Thread producer = new Thread( new Producer( "Producer", queue ) );
        producer.start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println();
                System.out.println("Thanks for using the application");
                System.out.println("Exiting...");
                printer.halt();
            }
        }));

        while ( true ) {
            if ( queue.getNumberOfJobs() > 0 && printerThread.getState() == State.WAITING ) {
                System.out.println("Notiying printer");

                synchronized ( printer ) {
                    printer.notify();
                }
            }

            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
