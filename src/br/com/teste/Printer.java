package br.com.teste;


public class Printer implements Runnable {
    private static final long MILLIS_PER_PAGE = 500;
    private boolean printing = false;
    private boolean finalize = false;

    private Queue queue;
    private String name;

    public Printer( String name, Queue queue ) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        synchronized ( this ) {
            System.out.println("Printer started");
            while ( true ) {

                if ( queue.isEmpty() ) {
                    try {
                        System.out.println("Printer waiting");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                PrintJob printJob = queue.removeFront();

                if ( printJob != null ) {
                    print(printJob);
                }

                if ( finalize ) {
                    break;
                }
            }
        }
    }

    protected void print( PrintJob printJob ) {
        System.out.println();
        System.out.println("Starting document printing: " + printJob.getJobsName());

        printing = true;
        long impressionTime = printJob.getNumberOfPages() * MILLIS_PER_PAGE;
        System.out.println("impressionTime  = " + impressionTime + " miliseconds");

        for ( long i = 0; i < impressionTime; i+=500 ) {
            try {
                //System.out.println("-");
                Thread.sleep( 500 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printing = false;
        System.out.println("End of document printing: " + printJob.getJobsName());
    }

    public void halt() {
        try {
            System.out.println("Finalize printer");
            finalize = true;

            while ( printing ) {
                try {
                    Thread.sleep( 100 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
