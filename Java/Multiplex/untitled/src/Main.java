import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

//encryp func each code with a copy of the array, append to array the result of hash the shared array
//string with bigger size, equal separate, each thread with a slice copy, run algorithm appends to shared string

class SharedCount1 extends Thread{
    Semaphore semaphoreA;
    String local_string;
    ArrayList<String> global_string;

    public SharedCount1(Semaphore semaphorea,String encryptString,ArrayList<String> global_one){
        this.local_string = encryptString;
        this.global_string = global_one;
        this.semaphoreA = semaphorea;
    }


    @Override
    public void run(){
    }

}
public class Main {
    public static void main(String[] args) throws InterruptedException {

//        ArrayList<Character> listOfChars = "Encrypt This String"



        String string_encr = "encrypt this friend this looks very good to be changed";
        // split string by no space
        String[] strSplit = string_encr.split(" ");

        ArrayList<String> str_list = new ArrayList<String>(
                Arrays.asList(strSplit));

        ArrayList<String> str_glob = new ArrayList<String>();
        Semaphore semaphorea = new Semaphore(5); //mutiplex
        SharedCount1 shared1 = new SharedCount1(semaphorea, str_list.get(0),);

        shared1.start();

        shared1.join();


        System.out.println("end");
    }
}