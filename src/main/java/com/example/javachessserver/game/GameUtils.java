package com.example.javachessserver.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameUtils {
    public static List<Integer> stringToMove(String moveString) {
        return new ArrayList<Integer>(Arrays.asList(
                Integer.parseInt(moveString.substring(0, 1)),
                Integer.parseInt(moveString.substring(1, 2)),
                Integer.parseInt(moveString.substring(2, 3)),
                Integer.parseInt(moveString.substring(3, 4))
        ));
    }

    public static boolean isValidMove(List<List<Integer>> board, List<Integer> move) {
        // TODO
        return true;
    }

    public static void applyMove(List<List<Integer>> board, List<Integer> move) {
        // TODO
    }
}
