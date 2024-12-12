import java.util.*;

public class HorseRaceATM {
    List<HorseDetails> horseList = new ArrayList<>();
    Map<Integer, Integer> inventory = new TreeMap<>(Collections.reverseOrder());
    int latestWinner = 1;
    public HorseRaceATM() {
        setUpHorseDetails();
        setUpInventory();
    }

    private void setUpInventory() {
        inventory.put(1, 10);
        inventory.put(5, 10);
        inventory.put(10, 10);
        inventory.put(20, 10);
        inventory.put(100, 10);
    }

    private void setUpHorseDetails() {
        //by default the first horse wins at the beginning
        horseList.add(new HorseDetails(1, "That Darn Gray Cat", 5, true));
        horseList.add(new HorseDetails(2, "Fort Utopia", 10, false));
        horseList.add(new HorseDetails(3, "Count Sheep", 9, false));
        horseList.add(new HorseDetails(4, "Ms Traitour", 4, false));
        horseList.add(new HorseDetails(5, "Real Princess", 3, false));
        horseList.add(new HorseDetails(6, "Pa Kettle", 5, false));
        horseList.add(new HorseDetails(7, "Gin Stinger", 6, false));
    }

    public static void main(String[] args) {
        HorseRaceATM horseRace = new HorseRaceATM();
        Scanner scanner = new Scanner(System.in);
        horseRace.printOptions();
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if(input.equals("R") || input.equals("r")) {
                //restock
                horseRace.restock();
            } else if(input.equals("Q") || input.equals("q")) {
                //Exit the application
                System.out.println("Exiting the application");
                break;
            } else if(input.startsWith("W ") || input.startsWith("w ")) {
                //need to get the horse number
                String[] s = input.split(" ");
                //set the winning horse number
                horseRace.setWinningHorse(s[1]);
            } else if(input.matches("^[0-9]+ [0-9]+$")) {
                //betting
                String[] s = input.split(" ");
                int horseNumber = Integer.parseInt(s[0]);
                int amount = Integer.parseInt(s[1]);
                horseRace.playBetting(horseNumber, amount);
            } else {
                System.out.println("Invalid Command: " + input);
            }
            horseRace.printOptions();
        }
        scanner.close();
    }

    private void playBetting(int horseNumber, int amount) {
        //validations
        if(horseNumber <1 || horseNumber>7) {
            System.out.println("Invalid Horse Number: " + horseNumber);
            return;
        }
        if(amount % 1 != 0) {
            System.out.println("Invalid bet: " + amount);
            return;
        }
        HorseDetails bettingHorse = horseList.get(horseNumber - 1);
        if(!bettingHorse.won) {
            System.out.println("No Payout: " + bettingHorse.name);
            return;
        }
        int payment = amount * bettingHorse.odds;
        //payout logic
        //using reverse TreeMap for optimal payment distribution
        Map<Integer, Integer> tmp = new TreeMap<>(Collections.reverseOrder());
        for(int i: inventory.keySet()) {
            int maxBills = Math.min(payment / i, inventory.get(i));
            tmp.put(i, maxBills);
            payment -= maxBills * i;
        }
        if(payment != 0) {
            System.out.println("Insufficient fund: " + payment);
            return;
        }
        System.out.println("Despensing:");
        for(int i: tmp.keySet()) {
            inventory.put(i, inventory.get(i) - tmp.get(i));
            System.out.println("$" + i + " ," + tmp.get(i));
        }
    }

    public void setWinningHorse(String s) {
        int n = Integer.parseInt(s);
        if(n>1 && n<7) {
            //set the winner
            for(HorseDetails horse : horseList) {
                if(horse.number == n) {
                    horse.won = true;
                } else {
                    horse.won = false;
                }
            }
            latestWinner = n;
        } else {
            System.out.println("Invalid Horse Number: " + s);
        }
    }

    private void restock() {
        //reset the inventory
        setUpInventory();
        System.out.println("Inventory restocked");
    }

    private void printOptions() {
        System.out.println("Inventory: ");
        inventory.forEach((key, value)-> System.out.println(key + ", " + value));
        System.out.println("Horses in race: ");
        horseList.forEach(horse -> System.out.println(horse.toString()));
    }
}
