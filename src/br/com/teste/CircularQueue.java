package br.com.teste;

import java.util.concurrent.LinkedBlockingQueue;


public class CircularQueue implements Queue {

    private LinkedBlockingQueue<PrintJob> queue;

    public CircularQueue( int limit ) {
        queue = new LinkedBlockingQueue<PrintJob>( limit );
    }

    @Override
    public void addBlack(PrintJob printJob) throws Exception {
        try {
            queue.add( printJob );

        } catch ( IllegalStateException e ) {
            e.printStackTrace();
            throw new FullQueueException();
        }
    }

    @Override
    public int getNumberOfJobs() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public PrintJob removeFront() {
        return queue.poll();
    }
}
