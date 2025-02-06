package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

//Cria um pool de 3 threads em teoria cada thread dentro do pool vai rodar uma task disponivel que for dada
// voce deve implemetar o callable do java para a task desejada no caso foi um bubble sort
//
public class Futuresss {
    private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);

    public static void main(String args[]) throws InterruptedException, ExecutionException {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        for (int i = 0 ; i < 100000 ; i ++ ){
            Random random = new Random();
            int randomNumber = random.nextInt(100) + 1;
            list1.add(randomNumber);
        }

        for (int i = 0 ; i < 100000 ; i ++ ){
            Random random = new Random();
            int randomNumber = random.nextInt(100) + 1;
            list2.add(randomNumber);
        }

        for (int i = 0 ; i < 100000 ; i ++ ){
            Random random = new Random();
            int randomNumber = random.nextInt(100) + 1;
            list3.add(randomNumber);
        }


        SortingAlist task1 = new SortingAlist(list1);
        SortingAlist task2 = new SortingAlist(list2);
        SortingAlist task3 = new SortingAlist(list3);

        System.out.println("Processing....");
        Future<ArrayList<Integer>> future1 = threadpool.submit(task1);
        Future<ArrayList<Integer>> future2 = threadpool.submit(task2);
        Future<ArrayList<Integer>> future3 = threadpool.submit(task3);
        while (!future1.isDone() && !future2.isDone() && !future3.isDone()){
            System.out.println("Not done 1 or 2 or 3");
            Thread.sleep(1);
        }

        System.out.println("Task ended");
        ArrayList<Integer> list4 = (ArrayList<Integer>) future1.get();
        ArrayList<Integer> list5 = (ArrayList<Integer>) future2.get();
        ArrayList<Integer> list6 = (ArrayList<Integer>) future3.get();

        for (int i = 0 ; i < list4.size();i++){
            System.out.print(""+list4.get(i));
        }
        System.out.println("");
        for (int i = 0 ; i < list5.size();i++){
            System.out.print(""+list5.get(i));
        }
        System.out.println("");
        for (int i = 0 ; i < list6.size();i++){
            System.out.print(""+list6.get(i));
        }

        threadpool.shutdown();
    }

    private static class SortingAlist implements Callable<ArrayList<Integer>>{
        private ArrayList<Integer> lista = new ArrayList<Integer>();
        public SortingAlist(ArrayList<Integer> list1){
            this.lista = list1;
        }

        @Override
        public ArrayList<Integer> call() throws Exception {
            for (int i = 0 ; i < lista.size();i++){
                for (int j = 0 ; j < (lista.size() - 1); j ++){
                    if (lista.get(j) > lista.get(j+1)){
                        int temp = lista.get(j);
                        lista.set(j, lista.get(j+1));
                        lista.set(j+1, temp);
                    }
                }
            }

            return lista;
        }
    }
}
