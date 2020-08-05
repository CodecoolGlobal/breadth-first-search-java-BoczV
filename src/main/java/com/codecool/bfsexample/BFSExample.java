package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;
import com.codecool.bfsexample.searchFunctions.bfs.BreadthFirstSearch;

import java.util.List;

public class BFSExample {


    private static void populateDB() {
        RandomDataGenerator generator = new RandomDataGenerator();
        List<UserNode> users = generator.generate();

        GraphPlotter graphPlotter = new GraphPlotter(users);
        checkFirstFunctionality(users, generator);

        System.out.println("Done!");
    }

    private static void checkFirstFunctionality(List<UserNode> users, RandomDataGenerator generator){
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

        UserNode userFrom = users.get(generator.randomGenNumber(users));
        UserNode userTo = users.get(generator.randomGenNumber(users));
        System.out.println("Minimum distance between " + userFrom.getFirstName() + " " + userFrom.getLastName() + "(" + userFrom.getId() + ")"
                + " and " + userTo.getFirstName() + " " + userTo.getLastName() + "(" + userTo.getId() + ")" + " was: "
                + breadthFirstSearch.returnMinimumDistance(userFrom, userTo));
    }

    public static void main(String[] args) {
        populateDB();
    }
}
