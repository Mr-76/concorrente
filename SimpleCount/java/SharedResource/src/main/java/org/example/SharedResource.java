package org.example;


import java.lang.reflect.Array;
import java.util.ArrayList;
//porque funciona ? o syncro cria um mutex, que so deixa acessar certa parte de memoria uma thread por vez no caso
//o incremento da varavel contador, se por acaso usarmos o outro metodo increment2 nao funcina pois eh sobreescrito
public class SharedResource {
    private int contador = 0;

    public synchronized void increment(){
        contador++;
    }

    public void increment2(){
        contador++;
    }

    //metodo para trabalhar com as threads
    //Criando 10 threads....
    //colocando na lista
    //depois inicializando todas as threads
    //depois esperando todas finalizarem para que a main thread faca o print
    public void start() throws InterruptedException {
        ArrayList<Thread> arrayOfThreads= new ArrayList<Thread>();
        for (int i = 0 ; i < 10 ;i++){
            Thread t1 = new Thread(new Runnable(){
                @Override
                public void run() {
                    for (int b = 0 ; b < 10000; b++){
                        increment2();
                    }
                }
            });
            arrayOfThreads.add(t1);
        }

        for (int b = 0 ; b <10; b ++){
            arrayOfThreads.get(b).start();
        }

        for (int b = 0 ; b <10; b ++){
            arrayOfThreads.get(b).join();
        }

       System.out.print("Counting is " + contador);
    }



    public static void main(String[] args) throws InterruptedException {
        SharedResource shared1 = new SharedResource();
        shared1.start();
    }

}