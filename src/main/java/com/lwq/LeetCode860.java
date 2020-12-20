package com.lwq;

/**
 * 860. 柠檬水找零
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 * <p>
 * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * <p>
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * <p>
 * 注意，一开始你手头没有任何零钱。
 * <p>
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[5,5,5,10,20]
 * 输出：true
 * 解释：
 * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
 * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
 * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
 * 由于所有客户都得到了正确的找零，所以我们输出 true。
 * 示例 2：
 * <p>
 * 输入：[5,5,10]
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：[10,10]
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：[5,5,10,10,20]
 * 输出：false
 * 解释：
 * 前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
 * 对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
 * 对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
 * 由于不是每位顾客都得到了正确的找零，所以答案是 false。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lemonade-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode860 {
    public static void main(String[] args) {
        lemonadeChange(new int[]{5, 5, 5, 10, 20});
    }

    /**
     * 5 美元，由于柠檬水的价格也为 5 美元，因此我们直接收下即可。
     * <p>
     * 10 美元，我们需要找回 5 美元，如果没有 5 美元面值的钞票，则无法正确找零。
     * <p>
     * 20 美元，我们需要找回 15 美元，此时有两种组合方式，一种是一张 10 美元和 5 美元的钞票，一种是 3 张 5 美元的钞票，
     * 如果两种组合方式都没有，则无法正确找零。当可以正确找零时，两种找零的方式中我们更倾向于第一种，
     * 即如果存在 5 美元和 10 美元，我们就按第一种方式找零，否则按第二种方式找零，
     * 因为需要使用 5 美元的找零场景会比需要使用 10 美元的找零场景多，我们需要尽可能保留 5 美元的钞票。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/lemonade-change/solution/ning-meng-shui-zhao-ling-by-leetcode-sol-nvp7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param bills
     * @return
     */
    public static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                five--;
                ten++;
            } else {
                // 说明是二十
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else {
                    five -= 3;
                }
            }
            if (five < 0) {
                return false;
            }
        }
        return true;
    }
}
