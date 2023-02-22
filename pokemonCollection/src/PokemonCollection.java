/*
 * CS2050 - Computer Science II - Fall 2022
 * Instructor: Thyago Mota
 * Student(s) Name(s):
 * Description: prg_03 - Pokemon Collection
 */

import java.util.Locale;

public class PokemonCollection extends BST<Pokemon> {

    private String type1;

    public PokemonCollection(String type1) {
        this.type1 = type1;
    }

    public PokemonCollection() {
        this("");
    }

    // TODO #6: add the given pokemon card to the collection; if type1 is defined, check if the given pokemon card has the same type1
    public void add(final Pokemon pokemon) {
        if(isEmpty())
            root = new BSTNode<>(pokemon);
        else{
            String type = pokemon.getType1();
            if(!type.equals("") && type.equals(type1)) {
                addRecursively(root, pokemon);
            }
        }


    }

    // TODO #7: similar to BST's searchRecursively, returning a Pokemon reference (instead of true/false); return null if the pokemon card is not found
    private Pokemon searchRecursively(final BSTNode<Pokemon> current, final Pokemon value) {
        if (value.compareTo(current.getValue()) == 0)
            return current.getValue();
        else if (value.compareTo(current.getValue()) < 0) {
            if (current.getLeft() == null)
                return null;
            else
                return searchRecursively(current.getLeft(), value);
        } else {
            if (current.getRight() == null)
                return null;
            else
                return searchRecursively(current.getRight(), value);
        }
    }

    // TODO #8: similar to BST's search; however, this method takes the name of a Pokemon and it should return avPokemon reference (instead of true/false); return null if the pokemon card is not
    public Pokemon search(final String name) {
        if(isEmpty())
            return null;
        else
            return searchRecursively(root, new Pokemon(name));
    }

    // TODO #9: return a string representation of a pokemon collection according to specifications in README file
    @Override
    public String toString() {
        String type11 = this.type1;
        if(type11 == null){
            return "Welcome to my Pokemon Cards Collection." + "\n" +
                    "There are " + size() + " pokemon cards." + "\n" +
                    "The pokemon cards are stored in a BST for optimized search." + "\n" +
                    "The current height of the BST is " + height();
        }
        else{
            return "Welcome to my Pokemon Cards Collection." + "\n" +
                    "This collection has pokemon cards of type1 = " + type1.toLowerCase(Locale.ROOT) + " only." + "\n" +
                    "There are " + size() + " pokemon cards." + "\n" +
                    "The pokemon cards are stored in a BST for optimized search." + "\n" +
                    "The current height of the BST is " + height();
        }

    }
}
