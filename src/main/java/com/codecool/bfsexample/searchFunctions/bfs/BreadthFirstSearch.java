package com.codecool.bfsexample.searchFunctions.bfs;

import com.codecool.bfsexample.model.UserNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BreadthFirstSearch {

    private int roundNumber;

    public int returnMinimumDistance(UserNode userFrom, UserNode userTo){
        roundNumber = 0;
        checkFriends(userFrom.getFriends(), userTo);
        return roundNumber;
    }

    private boolean checkFriends(Set<UserNode> friends, UserNode userTo){
        roundNumber++;
        Set<UserNode> friendsOfFriends = new HashSet<>();
        for(UserNode friend: friends){
            if(friend == userTo){
                return true;
            } else {
                friendsOfFriends.addAll(friend.getFriends());
            }
        }
        return checkFriends(friendsOfFriends, userTo);
    }

    public Set<UserNode> returnFriendsOfFriendsAtALevel(UserNode userNode, int distance){
        return new HashSet<UserNode>();
    }


    public List<UserNode> returnShortestPath(UserNode userFrom, UserNode userTo, int distance){
        return new ArrayList<>();
    }

}
