package br.com.teste;

import static org.junit.Assert.*;
import org.junit.Test;


public class CircularQueueTest {

    @Test
    public void shold_create_CircularQueue() throws Exception {
        try {
            CircularQueue queue = new CircularQueue(15 );

        } catch ( Exception e ) {
            fail("Error creating Queue");
        }
    }

    @Test
    public void shold_add_new_PrintJob() throws Exception {
        CircularQueue queue = new CircularQueue(15 );
        PrintJob job = new PrintJob( "Job 1", 10 );
        queue.addBlack( job );

        assertEquals( 1, queue.getNumberOfJobs() );
    }

    @Test
    public void shold_trows_FullQueueException_if_limit_of_jobs_exceeded() throws Exception {
        CircularQueue queue = new CircularQueue(2 );

        try {
            PrintJob job = new PrintJob( "Job 1", 10 );
            queue.addBlack( job );

            PrintJob job2 = new PrintJob( "Job 2", 10 );
            queue.addBlack( job2 );

            PrintJob job3 = new PrintJob( "Job 3", 10 );
            queue.addBlack( job3 );

            fail( "Limit not work" );

        } catch ( FullQueueException fqe ) {
            fqe.printStackTrace();

        }
    }

    @Test
    public void shold_get_and_remove_first_job_in_queue() throws Exception {
        CircularQueue queue = new CircularQueue(10 );

        try {
            PrintJob job = new PrintJob( "Job 1", 10 );
            queue.addBlack( job );

            PrintJob job2 = new PrintJob( "Job 2", 10 );
            queue.addBlack( job2 );

            PrintJob job3 = new PrintJob( "Job 3", 10 );
            queue.addBlack( job3 );

            PrintJob jobInQueue = queue.removeFront();

            assertEquals( "Job 1", jobInQueue.getJobsName() );

        } catch ( Exception fqe ) {
            fail(fqe.getMessage() );
        }
    }
}
