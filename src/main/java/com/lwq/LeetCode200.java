package com.lwq;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1:
 *
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 */
public class LeetCode200 {

    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int count = 0;
        for(int row = 0; row < grid.length ; row++){
            for(int col = 0 ; col < grid[0].length ; col++){
                if(grid[row][col] == '1'){
                    dfs(grid,row,col);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 感染函数，把当前1的位置的上下左右的1都改为2
     * @param grid
     * @param row
     * @param col
     */
    private void dfs(char[][] grid, int row, int col) {
        // 如果越界了，则返回
        if (!(0 <= row && row < grid.length && 0 <= col && col < grid[0].length)) {
            return;
        }
        // 如果不到岛屿，则返回
        if (grid[row][col] != '1') {
            return;
        }
        grid[row][col] = '2';
        // 向上下左右遍历
        dfs(grid,row+1,col);
        dfs(grid,row-1,col);
        dfs(grid,row,col+1);
        dfs(grid,row,col-1);
    }
}
