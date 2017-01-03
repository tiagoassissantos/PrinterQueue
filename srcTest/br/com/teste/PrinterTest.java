package br.com.teste;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Date;


public class PrinterTest {

    @Test
    public void shold_sleep_3_seconds_for_6_pages() throws Exception {

        Printer printer = new Printer( "Printer", null );

        long time1 = new Date().getTime();
        printer.print( new PrintJob( "Job 1", 6 ) );
        long time2 = new Date().getTime();

        long diference = time2 - time1;

        assertEquals( 3000l, diference, 5 );
    }

}
