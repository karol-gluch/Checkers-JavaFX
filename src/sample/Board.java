package sample;

public class Board extends Main {
    public static void initializeBoard() //pozycje startowe
    {
        for (int i = 0; i < (TilesPerRow); i += 2) {
            gameData[i][5] = RED;
            gameData[i][7] = RED;
        }
        for (int i = 1; i < (TilesPerRow); i += 2)
            gameData[i][6] = RED;

        for (int i = 1; i < (TilesPerRow); i += 2) {
            gameData[i][0] = WHITE;
            gameData[i][2] = WHITE;
        }
        for (int i = 0; i < (TilesPerRow); i += 2)
            gameData[i][1] = WHITE;

        for (int i = 0; i < (TilesPerRow); i += 2)
            gameData[i][3] = EMPTY;

        for (int i = 1; i < (TilesPerRow); i += 2)
            gameData[i][4] = EMPTY;
    }
}
