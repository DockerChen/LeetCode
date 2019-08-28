import class_03.Code_04_DogCatQueue;
import sun.util.locale.provider.FallbackLocaleProviderAdapter;

import java.util.LinkedList;
import java.util.Queue;

public class CatAndDog {
    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    public static class PetEnterQueue {
        private Pet pet;
        private long count;

        public PetEnterQueue(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return pet;
        }

        public long getCount() {
            return count;
        }

        public String getPetType() {
            return this.pet.getPetType();
        }

    }

    public static class CatandDogQueue {
        private Queue<PetEnterQueue> catQueue;
        private Queue<PetEnterQueue> dogQueue;
        private long count;

        public CatandDogQueue() {
            this.catQueue = new LinkedList<>();
            this.dogQueue = new LinkedList<>();
            this.count = 0;
        }

        public void add(Pet pet) {
            if (pet.getPetType().equals("cat")) {
                catQueue.add(new PetEnterQueue(pet, this.count++));
            } else if (pet.getPetType().equals("dog")) {
                dogQueue.add(new PetEnterQueue(pet, count++));
            } else {
                throw new RuntimeException(pet.getPetType() + "is not exist");
            }
        }

        public Pet pollAll() {
            if (!catQueue.isEmpty() && !dogQueue.isEmpty()) {
                if (catQueue.peek().getCount() < dogQueue.peek().getCount()) {
                    return catQueue.poll().getPet();
                } else {
                    return dogQueue.poll().getPet();
                }
            } else if (!catQueue.isEmpty()) {
                return catQueue.poll().getPet();
            } else if (!dogQueue.isEmpty()) {
                return dogQueue.poll().getPet();
            } else {
                throw new RuntimeException("queue is empty");
            }

        }

        public Pet pollDog() {
            if (!dogQueue.isEmpty()) {
                return dogQueue.poll().getPet();
            } else {
                throw new RuntimeException("dog queue is empty");
            }


        }

        public Pet pollCat() {
            if (!catQueue.isEmpty()) {
                return catQueue.poll().getPet();
            } else {
                throw new RuntimeException("cat queue is empty");
            }

        }

        public boolean isEmpty() {
            return catQueue.isEmpty() && dogQueue.isEmpty();


        }

        public boolean isCatEmpty() {
            return catQueue.isEmpty();


        }

        public boolean isDogEmpty() {
            return dogQueue.isEmpty();
        }

    }

    public static void main(String[] args) {
        CatandDogQueue test = new CatandDogQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isDogEmpty()) {
            System.out.println(test.pollDog().getPetType());
        }
        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getPetType());
        }
    }


}
