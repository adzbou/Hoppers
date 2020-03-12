public class LevelConfig{
    /**
     * NEED TO CREATE PRIVATE ACCESSOR get methods for these 
     * as they need to be encapsulated however it will be tedious to do one for each level
     * try to find more efficent ways to make use of encapsulation without writing a method for each level!!!!  
     */
    int [][] waterBoard = {
        {1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1},
    };

    int[][] level1 = {
        {3, 0, 3, 0, 1}, 
        {0, 0, 0, 0, 0}, 
        {3, 0, 1, 0, 1}, 
        {0, 1, 0, 1, 0}, 
        {1, 0, 1, 0, 1}, 
    };

    int [][] level2 = {
        {1, 0, 3, 0, 1}, 
        {0, 1, 0, 1, 0}, 
        {1, 0, 1, 0, 1}, 
        {0, 1, 0, 1, 0}, 
        {1, 0, 1, 0, 1}, 
    };
    
    LevelConfig(){  

    }
    





}