package com.codecool.bfsexample.searchFunctions.bfs;

import com.codecool.bfsexample.model.UserNode;

import java.util.*;
import java.util.stream.Collectors;

public class BreadthFirstSearch {

    private int roundNumber;

    public int returnMinimumDistance(UserNode userFrom, UserNode userTo){
        roundNumber = 0;
        Set<UserNode> visited = new HashSet<>();
        visited.add(userFrom);
        checkFriends(userFrom.getFriends(), userTo, visited);
        return roundNumber;
    }

    private boolean checkFriends(Set<UserNode> friends, UserNode userTo, Set<UserNode> visited){
        roundNumber++;
        Set<UserNode> friendsOfFriends = new HashSet<>();
        for(UserNode friend: friends){
            if(!visited.contains(friend)){
                if(friend == userTo){
                    return true;
                } else {
                    visited.add(friend);
                    friendsOfFriends.addAll(friend.getFriends());
                }
            }
        }
        return checkFriends(friendsOfFriends, userTo, visited);
    }



    public Set<UserNode> returnFriendsOfFriendsAtALevel(UserNode userNode, int distance){
        roundNumber = 1;
        Set<UserNode> friendsOfFriends = userNode.getFriends();
        Set<UserNode> visitedFriends = new HashSet<>();
        visitedFriends.add(userNode);
        friendsOfFriends = updateFriendsOfFriendsList(friendsOfFriends, visitedFriends, distance);
        return friendsOfFriends.stream().filter(person -> !visitedFriends.contains(person)).collect(Collectors.toSet());
    }

    private Set<UserNode> updateFriendsOfFriendsList(Set<UserNode> users, Set<UserNode> visitedFriends, int distance){
        Set<UserNode> newFriends = new HashSet<>();
        if(roundNumber == distance) {
            return users;
        }
        for(UserNode friend: users){
            newFriends.addAll(friend.getFriends());
            visitedFriends.add(friend);
        }
        roundNumber++;
        users = newFriends;
        return updateFriendsOfFriendsList(users, visitedFriends, distance);
    }





    public List<UserNode> returnShortestPath(UserNode userFrom, UserNode userTo, int distance){
        List<UserNode> allPeople = new ArrayList<>();
        allPeople.add(userFrom);
        allPeople.addAll(userFrom.getFriends());
        exploreAllListsOfPeople(allPeople, userFrom.getFriends(), userTo, userFrom);

        allPeople = allPeople.stream().distinct().collect(Collectors.toList());
        List<UserNode> shortestPath = findShortestPath(allPeople, userTo);

        shortestPath.add(userFrom);
        Collections.reverse(shortestPath);
        return shortestPath;
    }

    private boolean exploreAllListsOfPeople(List<UserNode> allPeople, Set<UserNode> friends, UserNode userTo, UserNode userFrom){
        Set<UserNode> friendsInTheSameLevel = new HashSet<>();
        for(UserNode friend: friends){
            if(friend.getFriends().contains(userTo)){
                return true;
            }
            friendsInTheSameLevel.addAll(friend.getFriends().stream().filter(element -> !element.equals(userFrom))
                    .collect(Collectors.toSet()));
        }
        allPeople.addAll(friendsInTheSameLevel);
        return exploreAllListsOfPeople(allPeople, friendsInTheSameLevel, userTo, userFrom);
    }

    private List<UserNode> findShortestPath(List<UserNode> allPeople, UserNode userTo){
        List<UserNode> shortestPath = new ArrayList<>();
        UserNode helperNode = userTo;
        for(int i = allPeople.size() - 1; i >= 0; i--){
            if(allPeople.get(i).getFriends().contains(helperNode)){
                shortestPath.add(helperNode);
                helperNode = allPeople.get(i);
            }
        }
        return shortestPath;
    }

}
