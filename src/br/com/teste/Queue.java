package br.com.teste;


public interface Queue {
    void addBlack( PrintJob printJob ) throws Exception;
    int getNumberOfJobs();
    boolean isEmpty();
    PrintJob removeFront();
}
