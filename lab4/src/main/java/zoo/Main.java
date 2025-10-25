package zoo;
import zoo.animals.Eagle;
import zoo.animals.Giraffe;
import zoo.animals.Lion;
import zoo.animals.Zebra;
import zoo.cages.BirdCage;
import zoo.cages.HoofedCage;
import zoo.cages.LionCage;

public class Main {
    public static void main(String[] args) throws Exception {
        Zoo myZoo = new Zoo();

        LionCage lionCage = new LionCage(2);
        HoofedCage hoofedCage = new HoofedCage(3);
        BirdCage birdCage = new BirdCage(2);

        Lion simba = new Lion("Simba");
        Lion mufasa = new Lion("Mufasa");

        Zebra marty = new Zebra("Marty");
        Giraffe melman = new Giraffe("Melman");

        Eagle sky = new Eagle("Sky");

        System.out.println("Adding lions...");
        lionCage.addAnimal(simba);
        lionCage.addAnimal(mufasa);

        System.out.println("Adding hoofed animals...");
        hoofedCage.addAnimal(marty);
        hoofedCage.addAnimal(melman);

        System.out.println("Adding birds...");
        birdCage.addAnimal(sky);

        myZoo.addCage(lionCage);
        myZoo.addCage(hoofedCage);
        myZoo.addCage(birdCage);

        System.out.println("\nTotal number of animals in the zoo: " + myZoo.getCountOfAnimals());
        System.out.println("In the lion cage: " + lionCage.getOccupied() + "/" + lionCage.getCapacity());

        System.out.println("\nReleasing Marty from the cage...");
        hoofedCage.removeAnimal(marty);
        System.out.println("Animals in the hoofed cage: " + hoofedCage.getOccupied());
        System.out.println("Total number of animals in the zoo: " + myZoo.getCountOfAnimals());
    }
}