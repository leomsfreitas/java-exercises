package aula06.exercicio03;

import java.util.ArrayList;

public class Zoo {
        private int totalJaulas;
        private final ArrayList<Animal> jaulas = new ArrayList<>();

        public Zoo() {
                this.totalJaulas = 10;

                Animal[] animals = { new Owl(), new Lion(), new Wolf() };
                for (int i = 0; i < totalJaulas; i++) {
                        int idx = i % animals.length;
                        jaulas.add(animals[idx]);
                }
        }

        public int getTotalJaulas() {
                return totalJaulas;
        }

        public void setTotalJaulas(int totalJaulas) {
                this.totalJaulas = totalJaulas;
        }

        public static void main(String[] args) {
                Zoo zoo = new Zoo();
                for (Animal animal : zoo.jaulas) {
                        animal.makeSound();

                        if (animal instanceof Lion) {
                                ((Lion) animal).run();
                        } else if (animal instanceof Wolf) {
                                ((Wolf) animal).run();
                        }
                }
        }
}
