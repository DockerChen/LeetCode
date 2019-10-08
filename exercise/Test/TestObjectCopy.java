package Test;

public class TestObjectCopy {
    public static class Dog implements Cloneable {
        private String id;
        private String name;

        public Dog(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public Dog clone() throws CloneNotSupportedException {
            Dog dog = (Dog) super.clone();

            return dog;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Dog{");
            sb.append("id='").append(id).append('\'');
            sb.append(", name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }


    }

    public static void main(String[] args) {
        Dog dog1 = new Dog("1", "Dog1");
        Dog dog2 = null;
        try {
            dog2 = dog1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        dog2.setName("Dog1 changed");

        System.out.println(dog1); // Dog{id='1', name='Dog1'}
        System.out.println(dog2); // Dog{id='1', name='Dog1 changed'}
    }

}
