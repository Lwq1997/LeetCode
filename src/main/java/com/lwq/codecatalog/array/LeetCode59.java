package com.lwq.codecatalog.array;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[[1]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 20
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode59 {
    public static void main(String[] args) {
        int[][] ints = new LeetCode59().generateMatrix(3);
        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    /**
     * 模拟顺时针画矩阵的过程:
     * <p>
     * 填充上行从左到右
     * 填充右列从上到下
     * 填充下行从右到左
     * 填充左列从下到上
     * <p>
     * ● 生成一个 n×n 空矩阵 mat，随后模拟整个向内环绕的填入过程：
     * ○ 定义当前左右上下边界 l,r,t,b，初始值 num = 1，迭代终止值 tar = n * n；
     * ○ 当 num <= tar 时，始终按照 从左到右 从上到下 从右到左 从下到上 填入顺序循环，每次填入后：
     * ■ 执行 num += 1：得到下一个需要填入的数字；
     * ■ 更新边界：
     * ● 例如从左到右填完后，上边界 t += 1，相当于上边界向内缩 1。
     * ● 例如从上到下填完后，右边界 r -= 1，相当于右边界向内缩 1。
     * ● 例如从右到左填完后，下边界 b -= 1，相当于下边界向内缩 1。
     * ● 例如从下到上填完后，左边界 l += 1，相当于左边界向内缩 1。
     * ○ 使用num <= tar而不是l < r || t < b作为迭代条件，是为了解决当n为奇数时，矩阵中心数字无法在迭代过程中被填充的问题。
     * <p>
     * 作者：jyd
     * 链接：https://leetcode.cn/problems/spiral-matrix-ii/solution/spiral-matrix-ii-mo-ni-fa-she-ding-bian-jie-qing-x/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1;
        //左右上下边界 l,r,t,b，初始值 num = 1，迭代终止值 max = n * n；
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int max = n * n;
        while (num <= max) {
            //从左到右
            for (int i = l; i <= r; i++) {
                res[t][i] = num++;
            }
            t++; // 更新上边界
            //从上到下
            for (int i = t; i <= b; i++) {
                res[i][r] = num++;
            }
            r--; // 更新右边界
            //从右到左
            for (int i = r; i >= l; i--) {
                res[b][i] = num++;
            }
            b--; // 更新下边界
            //从下到上
            for (int i = b; i >= t; i--) {
                res[i][l] = num++;
            }
            l++; // 更新左边界
        }
        return res;
    }
}
