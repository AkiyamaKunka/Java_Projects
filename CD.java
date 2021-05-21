import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class CD {
    private int totalRunningTime = 120; // unit is second
    private HashMap<String, Boolean> listRent = new HashMap<>();
    private List<String> namesOfCD;
    private HashMap<String, Integer> listSale = new HashMap<>();
    private static Semaphore semaphoreSale = new Semaphore(3);
    private static Semaphore semaphoreRent = new Semaphore(2);
    // two Sale thread can be allowed!
    class Restock extends Thread{
        @Override
        public void run(){
            int cnt = totalRunningTime;
            while(cnt-- > 0) {
                long startTime = System.currentTimeMillis();   //get current time
                for (int i = 0; i < 10; ++i) {
                    listSale.put(namesOfCD.get(i), 10);
                }
                long endTime=System.currentTimeMillis(); // get done time
                System.out.println("Restocked at time " + System.currentTimeMillis());
                try{
                    Thread.sleep(1000 - (endTime - startTime));
                    // make interval more precise
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    class Sale extends Thread{
        @Override
        public void run(){

            try{
                semaphoreSale.acquire(1);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            long startTime = System.currentTimeMillis();

            Random generator = new Random();
            int saleTime = generator.nextInt(201); // sale time from 0 to 200ms
            // sale all CD with random amount.
            for(int i = 0; i < 10; ++i){
                int saleNumber = generator.nextInt(6); // sale from 0 to 5
                String toSaleNameCD = namesOfCD.get(i);
                int remainCD = listSale.get(toSaleNameCD) - saleNumber;
                if(remainCD >= 0){
                    listSale.put(toSaleNameCD, remainCD);
                    System.out.println("Sale " + toSaleNameCD + " in amount of " + saleNumber
                            + " at time " + System.currentTimeMillis());
                }else{
                    int choice = generator.nextInt(2); // 1 wait, 0 give up
                    if(choice == 1){ // wait
                        emergentRestock();
                        while(listSale.get(toSaleNameCD) - saleNumber < 0){} // might be little dangerous
                        listSale.put(toSaleNameCD, listSale.get(toSaleNameCD) - saleNumber);
                        System.out.println("Sale " + toSaleNameCD + " in amount of " + saleNumber
                                + " at time " + System.currentTimeMillis());
                    }else{ // give up
                        System.out.println("Stock inadequate, give up waiting");

                        System.out.println("realease!");
                        semaphoreSale.release(1);
                        return;
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            long remainTime = 200 - (endTime - startTime);
            if(remainTime > 0){
                try{
                    Thread.sleep(remainTime);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("realease!");
            semaphoreSale.release(1);
        }
    }

    private void init(){
        namesOfCD = Arrays.asList("Music & Me", "Forever", "Ben", "Invincible",
                "HIStory: Past, Present and Future, Book 1",
                "Got to Be There", "Bad", "Dangerous", "Off the Wall", "Thriller");
        for(int i = 0; i < 10; ++i){
            listRent.put(namesOfCD.get(i), true);
        }
    }
    // ten most famous albums from Michael Jackson.
    public CD(){
        init();
        restock();
        sale();
    }

    public void sale(){
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        do {
            endTime = System.currentTimeMillis();
            if (semaphoreSale.availablePermits() > 0) {
                Thread t = new Sale();
                t.start();
                System.out.println("Sale Thread Created!");

            }

        }while ((int)(endTime - startTime) < totalRunningTime * 1000);
    }

    class Rent extends Thread{
        @Override
        public void run(){
            try{
                semaphoreRent.acquire(1);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            try{
                Thread.sleep(200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            long startTime = System.currentTimeMillis();
            Random generator = new Random();
            int rentTime = generator.nextInt(201); // sleep 200 ms

            // sale all CD with random amount.

                int rentNumber = generator.nextInt(10); // rent from 0 to 9
                String toRentNameCD = namesOfCD.get(rentNumber);
                boolean remainCD = listRent.get(toRentNameCD);
                if(remainCD){
                    listRent.put(toRentNameCD, false);
                    System.out.println("Rent " + toRentNameCD
                            + " at time " + System.currentTimeMillis());
                }else{
                    int choice = generator.nextInt(2); // 1 wait, 0 give up
                    if(choice == 1){ // wait
                        long startTimeWait = System.currentTimeMillis();
                        long endTimeWait = System.currentTimeMillis();
                        do {
                            endTimeWait = System.currentTimeMillis();
                            if (listRent.get(toRentNameCD)) {
                                listRent.put(toRentNameCD, false);
                                System.out.println("Rent " + toRentNameCD
                                        + " at time " + System.currentTimeMillis());
                                try{Thread.sleep(200);}
                                catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                                listRent.put(toRentNameCD, true);
                                break;
                            }
                        }while ((int)(endTimeWait - startTimeWait) < 200); // wait 200 ms
                    }else{ // give up
                        System.out.println("Rent inadequate, give up waiting");
                        System.out.println("releasee!");
                        semaphoreRent.release(1);
                        return;
                    }
                }
            semaphoreRent.release(1);
        }
    }

    public void setTotalRunningTime(int userIntendedTotalRunningTime) {
        totalRunningTime = userIntendedTotalRunningTime;
    }
    private void restock(){
        Thread t = new Restock();
        t.start();
    }
    private void emergentRestock(){
        for (int i = 0; i < 10; ++i) {
            listSale.put(namesOfCD.get(i), 10);
        }
        System.out.println("Emergent Restock done!");
    }

    public void rent(){
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        do {
            endTime = System.currentTimeMillis();
            if (semaphoreRent.availablePermits() > 0) {
                Thread t = new Rent();
                t.start();
                System.out.println("Rent Thread Created!");
            }
        }while ((int)(endTime - startTime) < totalRunningTime * 1000);

    }
}

