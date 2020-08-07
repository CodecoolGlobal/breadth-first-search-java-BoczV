package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;
import com.codecool.bfsexample.searchFunctions.bfs.BreadthFirstSearch;

import java.util.List;
import java.util.Set;

public class BFSExample {


    private static void populateDB() {
        RandomDataGenerator generator = new RandomDataGenerator();
        List<UserNode> users = generator.generate();

        GraphPlotter graphPlotter = new GraphPlotter(users);
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

        /*checkFirstFunctionality(breadthFirstSearch, users, generator);
        checkSecondFunctionality(breadthFirstSearch, users, generator, 3);*/

        checkThirdFunctionality(breadthFirstSearch, users, generator, 1);
        System.out.println("Done!");
    }

    private static void checkFirstFunctionality(BreadthFirstSearch breadthFirstSearch, List<UserNode> users, RandomDataGenerator generator){

        UserNode userFrom = users.get(generator.randomGenNumber(users));
        UserNode userTo = users.get(generator.randomGenNumber(users));
        System.out.println("Minimum distance between " + userFrom.getFirstName() + " " + userFrom.getLastName() + "(" + userFrom.getId() + ")"
                + " and " + userTo.getFirstName() + " " + userTo.getLastName() + "(" + userTo.getId() + ")" + " was: "
                + breadthFirstSearch.returnMinimumDistance(userFrom, userTo));
    }

    private static void checkSecondFunctionality(BreadthFirstSearch breadthFirstSearch, List<UserNode> users, RandomDataGenerator generator, int distance){
        UserNode userFrom = users.get(generator.randomGenNumber(users));
        Set<UserNode> results = breadthFirstSearch.returnFriendsOfFriendsAtALevel(userFrom, distance);
        System.out.println(userFrom.getFirstName() + " " + userFrom.getLastName() + "(" + userFrom.getId() + "): " + results);
    }


    private static void checkThirdFunctionality(BreadthFirstSearch breadthFirstSearch, List<UserNode> users, RandomDataGenerator generator, int distance){
        UserNode userFrom = users.get(generator.randomGenNumber(users));
        UserNode userTo = users.get(generator.randomGenNumber(users));
        List<UserNode> result = breadthFirstSearch.returnShortestPath(userFrom, userTo, distance);
        System.out.println("Shortest path between " + userFrom.getFirstName() + " "
                + userFrom.getLastName() + "(" + userFrom.getId() + ") and " + userTo.getFirstName() + " "
                + userTo.getLastName() + "(" + userTo.getId() + ") is " + result);
    }

    public static void main(String[] args) {
        populateDB();
    }
}
