package zoo;

import org.junit.jupiter.api.Test;
import zoo.animals.Eagle;
import zoo.animals.Giraffe;
import zoo.animals.Lion;
import zoo.animals.Zebra;
import zoo.cages.BirdCage;
import zoo.cages.HoofedCage;
import zoo.cages.LionCage;

import static org.junit.jupiter.api.Assertions.*;

public class ZooTest {

    @Test
    void testLionCage() throws Exception {
        LionCage cage = new LionCage(2);
        cage.addAnimal(new Lion("Simba"));
        cage.addAnimal(new Lion("Mufasa"));

        assertEquals(2, cage.getOccupied());

        Exception ex = assertThrows(Exception.class,
                () -> cage.addAnimal(new Lion("Scar")));
        assertEquals("Too many animals", ex.getMessage());
    }

    @Test
    void testRemoveAnimal() throws Exception {
        BirdCage cage = new BirdCage(1);
        Eagle eagle = new Eagle("Sky");
        cage.addAnimal(eagle);

        assertEquals(1, cage.getOccupied());

        cage.removeAnimal(eagle);
        assertEquals(0, cage.getOccupied());

        Exception ex = assertThrows(Exception.class,
                () -> cage.removeAnimal(eagle));
        assertEquals("There is no given animal", ex.getMessage());
    }

    @Test
    void testZooAnimalCount() throws Exception {
        Zoo zoo = new Zoo();

        LionCage lions = new LionCage(2);
        lions.addAnimal(new Lion("Simba"));

        HoofedCage hoofeds = new HoofedCage(3);
        hoofeds.addAnimal(new Zebra("Marty"));
        hoofeds.addAnimal(new Giraffe("Melman"));

        BirdCage birds = new BirdCage(2);
        birds.addAnimal(new Eagle("Sky"));

        zoo.addCage(lions);
        zoo.addCage(hoofeds);
        zoo.addCage(birds);

        assertEquals(4, zoo.getCountOfAnimals());
    }
}
