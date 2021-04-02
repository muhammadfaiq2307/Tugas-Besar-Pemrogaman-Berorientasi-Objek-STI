import java.util.Scanner;

public class Test {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        HijiTimer timer = new HijiTimer();

        timer.start();
        while (timer.isAlive()){
            String interruptInput=in.next();
            System.out.println(interruptInput);
            if (interruptInput.equals("STOP")){
                timer.interrupt();
                System.out.println("In");
            }
        }
//        inputThread.start();
    }
    public static class HijiTimer extends Thread{
        @Override
        public void run() {
            super.run();
            try{
                Thread.sleep(3000);
                System.out.println("Times up!");
            }
            catch (InterruptedException e){
                System.out.println("Interrupted");
            }
        }
    }
}
