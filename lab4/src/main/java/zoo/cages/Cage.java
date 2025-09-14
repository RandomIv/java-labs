package zoo.cages;

import zoo.animals.Animal;

import java.util.ArrayList;
import java.util.List;

abstract public class Cage<T extends Animal> {
    private final int capacity;
    private final List<T> animals = new ArrayList<>();

    public Cage(int capacity) {
            this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public int getOccupied() {
        return animals.size();
    }
    public void addAnimal(T animal) throws Exception {
        if (animals.size() >= capacity) {
            throw new Exception("Too many animals");
        }
        animals.add(animal);
    }
    public void removeAnimal(T animal) throws Exception {
        if (animals.isEmpty()) {
            throw new Exception("There is no given animal");
        }
        animals.remove(animal);
    }
    public List<T> getAnimals() {
        return animals;
    }
}