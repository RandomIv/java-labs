package zoo;

import zoo.animals.Animal;
import zoo.cages.Cage;

import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private final List<Cage<? extends Animal>> cages = new ArrayList<>();
    public void addCage(Cage<? extends Animal> cage) {
        cages.add(cage);
    }
    public int getCountOfAnimals (){
        return cages.stream()
                .mapToInt(cage -> cage.getAnimals().size())
                .sum();
    }
    public List<Cage<? extends Animal>> getCages() {
        return cages;
    }
}