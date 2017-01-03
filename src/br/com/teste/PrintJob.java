package br.com.teste;


public class PrintJob {
    private String name;
    private int numberOfPages = 0;

    public PrintJob( String name, int numberOfPages ) {
        this.name = name;
        this.numberOfPages = numberOfPages;
    }

    public String getJobsName() {
        return name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}
