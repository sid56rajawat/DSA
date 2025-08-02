import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidSudoku {
    private record Coordinate(int x, int y) {}

    public boolean isValidSudoku(char[][] board) {
        return validRows(board) && validColumns(board) && validBoxes(board);
    }

    private boolean validRows(char[][] board) {
        for(var i=0; i<9; i++) {
            List<Coordinate> coordinates = new ArrayList();
            for(var j=0; j<9; j++) {
                coordinates.add(new Coordinate(i, j));
            }
            if(!uniqueValuesAtCoordinates(coordinates, board)) return false;
        }
        return true;
    }

    private boolean validColumns(char[][] board) {
        for(var i=0; i<9; i++) {
            List<Coordinate> coordinates = new ArrayList();
            for(var j=0; j<9; j++) {
                coordinates.add(new Coordinate(j, i));
            }
            if(!uniqueValuesAtCoordinates(coordinates, board)) return false;
        }
        return true;
    }

    private boolean validBoxes(char[][] board) {
        for(var i=0; i<=6; i += 3) {
            for(var j=0; j<=6; j += 3) {
                Coordinate boxCorner = new Coordinate(i, j);
                if(!uniqueValuesAtCoordinates(boxStartingAt(boxCorner), board)) return false;
            }
        }
        return true;
    }

    private List<Coordinate> boxStartingAt(Coordinate boxCorner) {
        List<Coordinate> boxCoordinates = new ArrayList();
        for(var i=boxCorner.x; i < (boxCorner.x + 3); i++) {
            for(var j=boxCorner.y; j < (boxCorner.y + 3); j++) {
                boxCoordinates.add(new Coordinate(i, j));
            }
        }
        return boxCoordinates;
    }

    private boolean uniqueValuesAtCoordinates(List<Coordinate> coordinates, char[][] board) {
        Set<Character> visited = new HashSet();
        for(var c : coordinates) {
            char value = board[c.x][c.y];
            if(value == '.') { continue; }
            if(visited.contains(value)){ return false; }
            visited.add(value);
        }
        return true;
    }
}