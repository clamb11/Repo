/*
 * CS2050 - Computer Science II - Fall 2022
 * Instructor: Thyago Mota
 * Student(s) Name(s):
 * Description: prg_03 - PokemonDriver
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PokemonDriver {

    // TODO #10: open the CSV filename and use it to create a return a PokemonCollection of the given type1
    static PokemonCollection load(String filename, String type1) throws FileNotFoundException {

        PokemonCollection pk = new PokemonCollection(type1);
        Scanner in = new Scanner(new FileInputStream(filename));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String data[] = line.split(",");
            String type1Temp = data[2];
            if (type1.equals(type1Temp)) {
                pk.add(new Pokemon(data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]), Integer.parseInt(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[9]), Integer.parseInt(data[10]), Integer.parseInt(data[11]), Boolean.parseBoolean(data[12])));
            }

        }
        return pk;
    }

    // TODO #11: create a PokemonCollection using "load" and then allow users to search for pokemon cards in the collection
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Which type would you like to add to your collection?: ");
        Scanner scan = new Scanner(System.in);
        String tempType1 = scan.nextLine();
        PokemonCollection pc2 = load("pokemon.csv", tempType1);
        String nicePc2 = pc2.toString();
        System.out.println(nicePc2);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the pokemon to search for (blank to quit): ");
        String name = sc.nextLine();
        while (!name.equals("")) {
            Pokemon pk1 = new Pokemon(name);
            Pokemon pk2 = pc2.search(name);
            if (pk2 == null) {
                System.out.println("Pokemon with name of " + name + " was not found!");
                System.out.println("Enter the name of the pokemon to search for (blank to quit): ");
                name = sc.nextLine();
            } else {
                pk1.setAttack(pk2.getAttack());
                pk1.setDefense(pk2.getDefense());
                pk1.setGeneration(pk2.getGeneration());
                pk1.setHp(pk2.getHp());
                pk1.setLegendary(pk2.isLegendary());
                pk1.setSpAttack(pk2.getSpAttack());
                pk1.setSpeed(pk2.getSpeed());
                pk1.setType2(pk2.getType2());
                pk1.setTotal(pk2.getTotal());
                pk1.setType1(pk2.getType1());
                String nicePk1 = pk1.toString();
                System.out.println(nicePk1);
                System.out.println("Enter the name of the pokemon to search for (blank to quit): ");
                name = sc.nextLine();

            }
        }
        Scanner scanny = new Scanner(System.in);
        System.out.println("What pokemon would you like to remove? (blank to quit): ");
        String toBeRemoved = scanny.nextLine();
        while(!toBeRemoved.equals("")){
            Pokemon pkRemoving = new Pokemon(toBeRemoved);
            pc2.remove(pkRemoving);
            System.out.println("What pokemon would you like to remove? (blank to quit): ");
            toBeRemoved =scanny.nextLine();
        }
        Scanner scann = new Scanner(System.in);
        System.out.println("What pokemon would you like to add? (blank to quit)");
        String toBeAdded = scann.nextLine();
        while(!toBeAdded.equals("")){
            Pokemon pkAdding = new Pokemon(toBeAdded);
            pkAdding.setType1(tempType1);
            pc2.add(pkAdding);
            System.out.println("What pokemon would you like to add? (blank to quit)");
            toBeAdded = scann.nextLine();
        }


    }
}

