package br.com.teste;

import java.util.Random;


public class Producer implements Runnable {

    private Queue queue;
    private String name;

    public Producer( String name, Queue queue ) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        Random random = new Random();
        int numberOfJobs = random.nextInt( 11 ) + 10;

        for ( int i = 1; i <= numberOfJobs; i++ ) {
            System.out.println("Job = " + i);
            int numberOfPages = random.nextInt(20 ) + 1;
            PrintJob job = new PrintJob( "Document " + i, numberOfPages );

            try {
                queue.addBlack( job );

            } catch (FullQueueException e) {
                e.printStackTrace();
            }  catch (Exception e) {
                e.printStackTrace();
            }

            try {
                long wait = random.nextInt( 4000 ) + 1000;
                Thread.sleep( wait );

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
