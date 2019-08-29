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

    public static class PetEnter {
        private Pet pet;
        private long count;

        public PetEnter(Pet pet, long count) {
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
        private Queue<PetEnter> catQueue;
        private Queue<PetEnter> dogQueue;
        private long count;

        public CatandDogQueue() {
            this.catQueue = new LinkedList<>();
            this.dogQueue = new LinkedList<>();
            this.count = 0;
        }
//往队列中添加pet
        public void add(Pet pet) {
            if (pet.getPetType().equals("cat")) {
                catQueue.add(new PetEnter(pet, this.count++));
            } else if (pet.getPetType().equals("dog")) {
                dogQueue.add(new PetEnter(pet, count++));
            } else {
                throw new RuntimeException(pet.getPetType() + "is not exist");
            }
        }
//将队列中所有的实例按照进队列的先后顺序依次弹出
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
//        将队列中dog类的实例按照进队列的先后顺序依次弹出
        public Pet pollDog() {
            if (!dogQueue.isEmpty()) {
                return dogQueue.poll().getPet();
            } else {
                throw new RuntimeException("dog queue is empty");
            }


        }
//        将队列中cat类的实例按照进队列的先后顺序依次弹出
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
