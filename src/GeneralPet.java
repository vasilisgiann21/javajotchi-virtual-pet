import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*I use AtomicInteger instead of normal integer type variable so i can prevent seperate threads trying to update a shared static int at the same time.
IN my case i have created a variable called activePets, that counts how many pets are still alive. If i had a simple static int activePets instead of
an AtomicInteger and 2 pets died at same millisecond:
Thread A: reads the counter value =2
Thread B: reads the counter value =2
Thread A: subtracts 1 and writes 1
Thread B: subtracts 1 and writes 1
So even though 2 pets died, the counter says 1 . So the exit condition will never be met*/


abstract class GeneralPet {
    private String name;
    private boolean isAlive;
    private static final AtomicInteger activePets = new AtomicInteger(0);
    private Stat hunger;
    private Stat happiness;
    private int happinessTicks;
    private boolean hasUpgraded = false;
    private int boost = 5;

    public  GeneralPet(String name, int startingHunger, int startingHappiness){
        this.name = name;
        this.hunger = new Stat(startingHunger,startingHunger,"Hunger");
        this.happiness = new Stat(startingHappiness,startingHappiness,"Happiness");
        isAlive = true;
        activePets.incrementAndGet();

    }

    public abstract void makeSound();

    public String getName() {return name;}

    public boolean getIsAlive(){return isAlive;}

    public void setIsAlive(boolean variable){
        this.isAlive = variable;
    }

    public Stat getHunger(){
        return hunger;
    }

    public Stat getHappiness(){
        return happiness;
    }

    protected void startHeartbeat(){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true); // This makes it a background process
            return thread;
        });

        scheduler.scheduleAtFixedRate(() -> {
            this.hunger.decreaseValue();
            this.happiness.decreaseValue();
            if ( this.getHunger().getCurrentValue() <= 3 ) {java.awt.Toolkit.getDefaultToolkit().beep();}
            System.out.println("\n[System]:  " + name + " got a bit hungrier...");
            if (this.getHunger().getCurrentValue() <= 0) {
                if (this.getIsAlive()){
                    this.setIsAlive(false);
                    System.out.println("I am sorry... " + this.getName() + " is dead. 💀");


                    scheduler.shutdown();

                    if (activePets.decrementAndGet() == 0){
                        System.out.println("\n[System]: All pets are gone. Closing programm... ");
                        System.exit(0);}
                }
            }

            if (this.getHappiness().getCurrentValue() > 5)  {happinessTicks += 1; } else {happinessTicks = 0;}

            if (!hasUpgraded){
                if (happinessTicks == 24){
                    this.name = "super " + name  ;
                    this.hasUpgraded = true;
                    System.out.println("Your pet has been upgraded to: " + this.name );
                    this.getHappiness().upgradeMax(boost);

                }
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    public void feed(){
        this.getHunger().increaseValue();
        System.out.println(getName() + " has been fed ! ");
    }

    public void play(){
        this.getHappiness().increaseValue();
        System.out.println("Yoy played with : " + getName() + " ! ");
    }

    public void checkStatus(){
        System.out.println("\n-----STATUS-REPORT-----");
        System.out.println("Name : " + this.getName());
        this.makeSound();
        System.out.println(this.getHunger().toString());
        System.out.println(this.getHappiness().toString());
        System.out.println("------------------------\n");
    }


}

