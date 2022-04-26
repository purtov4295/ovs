public class GameLogic {

    private char[][] board;

    public void setCurrentMove(char currentMove) {
        this.currentMove = currentMove;
    }

    private char currentMove;
    public final int SIZE = 3;

    GameLogic() {
        board = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '_';
            }
        }

        currentMove = 'x';
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buffer.append(board[i][j]);
            }
        }
        return buffer.toString();
    }

    public boolean setBoard(String board2D) {
        if (board2D.length() == SIZE * SIZE) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    board[i][j] = board2D.charAt(i * SIZE + j);
                }
            }
        } else {
            System.out.println(ConsoleColors.RED_BOLD
                    + "Received value of invalid format."
                    + ConsoleColors.RESET);

            return false;
        }

        return true;
    }

    public char currentMove() {
        return currentMove;
    }

    private void changeActivePlayer() {
        if (currentMove == 'x')
            currentMove = 'o';
        else
            currentMove = 'x';
    }

    public boolean process(String move) {
        String [] x_y = move.split(":");

        if (x_y.length == 2) {
            try {
                int x = Integer.parseInt(x_y[0]);
                int y = Integer.parseInt(x_y[1]);

                if (x >= SIZE || x < 0 || y >= SIZE || y < 0)
                    return false;


                if (board[x][y] != '_')
                    return true;

                board[x][y] = currentMove;
                changeActivePlayer();

            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED_BOLD
                        + "Received invalid coordinates of the opponent's move."
                        + ConsoleColors.RESET);

                return false;
            }
        } else {
            System.out.println(ConsoleColors.RED_BOLD
                    + "Received invalid coordinates of the opponent's move."
                    + ConsoleColors.RESET);

            return false;
        }

        return true;
    }

    public char getWinner() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkFrom(i, j))
                    return board[i][j];
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '_')
                    return '_';
            }
        }
        return 'P';
    }

    private boolean checkFrom(int x, int y) {
        char player = board[x][y];
        if (player == '_')
            return false;

        boolean xWin = true, yWin = true, xyWin = true, yxWin = true;
        for (int i = 0; i < 3; i++) {
            if (x + i >= SIZE || board[x + i][y] != player)
                xWin = false;
            if (y + i >= SIZE || board[x][y + i] != player)
                yWin = false;
            if (y + i >= SIZE || x + i >= SIZE || board[x + i][y + i] != player)
                xyWin = false;
            if (y - i < 0 || x + i >= SIZE || board[x + i][y - i] != player)
                yxWin = false;
        }

        return xyWin || xWin || yWin || yxWin;
    }
}