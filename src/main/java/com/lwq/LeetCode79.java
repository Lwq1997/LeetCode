package com.lwq;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *  
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 */
public class LeetCode79 {
    // 定义方向
    int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col ; j++){
                if(board[i][j] == word.charAt(0)){
                    if(dfs(board,visited,i,j,word,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] visited, int x, int y, String word, int index) {
        if(index == word.length() -1 ){
            return board[x][y] == word.charAt(index);
        }
        if(board[x][y] == word.charAt(index)){
            visited[x][y] = true;
            for(int i = 0; i < 4 ; i++){
                int nx = x + direction[i][0];
                int ny = y + direction[i][1];
                if (inArea(nx, ny,board) && !visited[nx][ny]) {
                    if(dfs(board,visited,nx,ny,word,index+1)){
                        return true;
                    }
                }
            }
            visited[x][y] = false;
        }
        return false;
    }

    private boolean inArea(int nx, int ny, char[][] board) {
        return nx >= 0 && nx < board.length && ny >=0 && ny < board[0].length;
    }
}
