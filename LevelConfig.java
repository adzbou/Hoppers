public class LevelConfig{
    /**
     * NEED TO CREATE PRIVATE ACCESSOR get methods for these 
     * as they need to be encapsulated however it will be tedious to do one for each level
     * try to find more efficent ways to make use of encapsulation without writing a method for each level!!!!  
     */

    /**
     * 0 = Lilypad
     * 1 = Water
     * 2 = Greenfrog
     * 3 = RedFrog
     */


    int [][] defaultBackground ={
        {0, 1, 0, 1, 0},
        {1, 0, 1, 0, 1},
        {0, 1, 0, 1, 0},
        {1, 0, 1, 0, 1},
        {0, 1, 0, 1, 0},
    };

    private int [][][] levels = {
        {
            {0, 1, 0, 1, 0},
            {1, 2, 1, 2, 1},
            {0, 1, 2, 1, 0},
            {1, 0, 1, 0, 1},
            {2, 1, 3, 1, 2},
        },
        {
            {3, 1, 0, 1, 0},
            {1, 2, 1, 0, 1},
            {0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0},
        },
        {
            {0, 1, 0, 1, 3},
            {1, 0, 1, 0, 1},
            {0, 1, 0, 1, 2},
            {1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0},
        },
        {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 2, 1},
            {0, 1, 3, 1, 0},
            {1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0},
        },
        {
            {3, 1, 0, 1, 0},
            {1, 2, 1, 0, 1},
            {0, 1, 0, 1, 0},
            {1, 0, 1, 2, 1},
            {0, 1, 0, 1, 0},
        },
        {
            {0, 1, 2, 1, 0},
            {1, 0, 1, 0, 1},
            {2, 1, 0, 1, 0},
            {1, 0, 1, 0, 1},
            {3, 1, 0, 1, 0},
        },
        {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1},
            {0, 1, 2, 1, 3},
            {1, 2, 1, 2, 1},
            {0, 1, 0, 1, 0},
        },
        {
            {0, 1, 2, 1, 3},
            {1, 0, 1, 0, 1},
            {2, 1, 0, 1, 2},
            {1, 0, 1, 0, 1},
            {0, 1, 2, 1, 0},
        },
        {
            {0, 1, 3, 1, 0},
            {1, 2, 1, 2, 1},
            {0, 1, 0, 1, 0},
            {1, 2, 1, 2, 1},
            {0, 1, 0, 1, 0},
        },
        {
            {0, 1, 2, 1, 0},
            {1, 2, 1, 2, 1},
            {0, 1, 3, 1, 2},
            {1, 0, 1, 2, 1},
            {0, 1, 0, 1, 0},
        },
        {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0},
        },
    };
    
    
    /**
     * Searches the 3D array called levels for the 2D array of each level
     */
    int[][] getLevel(int num){
        return levels[num];
    }

    int getFrogs(int num){
        int frogCount = 0;
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                if (levels[num][i][j] == 2){
                    frogCount += 1;
                }
            }
        }
        return(frogCount);
    }

}