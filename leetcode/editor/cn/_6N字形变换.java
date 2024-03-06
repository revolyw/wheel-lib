/**
 * <p>将一个给定字符串 <code>s</code> 根据给定的行数 <code>numRows</code> ，以从上往下、从左到右进行&nbsp;Z 字形排列。</p>
 *
 * <p>比如输入字符串为 <code>"PAYPALISHIRING"</code>&nbsp;行数为 <code>3</code> 时，排列如下：</p>
 *
 * <pre>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R</pre>
 *
 * <p>之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如：<code>"PAHNAPLSIIGYIR"</code>。</p>
 *
 * <p>请你实现这个将字符串进行指定行数变换的函数：</p>
 *
 * <pre>
 * string convert(string s, int numRows);</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "PAYPALISHIRING", numRows = 3
 * <strong>输出：</strong>"PAHNAPLSIIGYIR"
 * </pre>
 *
 * <strong>示例 2：</strong>
 *
 * <pre>
 * <strong>输入：</strong>s = "PAYPALISHIRING", numRows = 4
 * <strong>输出：</strong>"PINALSIGYAHRPI"
 * <strong>解释：</strong>
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "A", numRows = 1
 * <strong>输出：</strong>"A"
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 1000</code></li>
 * <li><code>s</code> 由英文字母（小写和大写）、<code>','</code> 和 <code>'.'</code> 组成</li>
 * <li><code>1 &lt;= numRows &lt;= 1000</code></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>字符串</li></div></div><br><div><li>👍 2125</li><li>👎 0</li></div>
 */

public class _6N字形变换 {
    public static void main(String[] args) {
        Solution solution = new _6N字形变换().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String convert(String s, int numRows) {
            //普通解法
//            return normalSolution(s, numRows);
            //矩阵解法
            return matrixSolution(s, numRows);
        }

        /**
         * 矩阵解法。思路如下：
         * n 为 s 的长度，r 为 numRows。
         * 则当我们在矩阵上填写字符时，会向下填写 r 个字符，然后向右上继续填写 r−2 个字符，最后回到第一行
         * 因此 Z 字形变换的周期字符数 t=r+r−2=2r−2
         * 每个周期会占用矩阵上的 1+r−2=r−1 列。
         * 创建一个 r 行 c 列的矩阵，然后遍历字符串 s 并按 Z 字形填写。
         * 因此我们有 n/t 向上取整个周期（最后一个周期视作完整周期）
         * 乘上每个周期的列数，得到矩阵的列数 c=n/t 向上取整 * (r-1)
         * 具体来说，设当前填写的位置为 (x,y)，即矩阵的 x 行 y 列。初始 (x,y)=(0,0)，即矩阵左上角。
         * 若当前字符下标 i 满足 i mod t < r−1，当前是周期内第 r 个以内的字符时，说明是要填满自字符的列，则向下移动，否则为填空字符的列，向右上移动。
         * 填写完成后，逐行扫描矩阵中的非空字符，组成答案
         *
         * 时间复杂度：O(n)，其中 n==len(s)
         * 空间复杂度：O(n)
         * @param s 字符串
         * @param numRows 行数
         * @return 变换后的字符串
         */
        private String matrixSolution(String s, int numRows) {
            if (s.length() == 1 || numRows >= s.length()) {
                return s;
            }
            //一个周期的字符数
            int charNumsPerT = 2 * numRows - 2;
            //周期数
            int t = (int) Math.ceil((double) s.length() / charNumsPerT);
            //一个周期的列数
            int ct = numRows - 1;
            //总列数
            int c = t * ct;
            char[][] matrix = new char[numRows][c];
            for (int index = 0, x=0, y=0; index < s.length(); index++) {
                matrix[x][y] = s.charAt(index);
                if (index % t < numRows - 1) {
                    y++;
                } else {
                    x++; y--;
                }
            }
            StringBuilder result = new StringBuilder(s);
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < c; j++) {
                    if (matrix[i][j] != '\u0000') {
                        result.append(matrix[i][j]);
                    }
                }
            }
            return result.toString();
        }

        /**
         * 普通解法。时间复杂度=空间复杂度=O(n*m+m^2)，其中 n 为字符串长度，m 为 numRows
         * 思路：构造一个二维数组，用于存储所有字符
         *      含有 2 种列：全字符列、单个字符列
         *      全字符列：每列排列 numRows 个字符，如果不足 numRows 个字符则尽可能排
         *      单个字符列：1 个字符 + numRow - 1 个空格
         *      每两个完全列之间含有 numRows - 2 个单字符列
         *
         *      一个 Z 字周期有: 一个完全列 + (numRows - 2) 个单字符列
         *      所以一个周期的字符数 = numRows + numRows - 2 = 2 * numRows - 2
         *      一个周期 t 含有 numRows - 1 个列
         *      最后一个周期可能不完整，当做完整周期来算，用原字符串字符尽可能补充
         *
         * @param s       字符串
         * @param numRows 行数
         * @return 变换后的字符串
         */
        private String normalSolution(String s, int numRows) {
            int total = s.length();
            if (numRows <= 1 || numRows > s.length()) {
                return s;
            }
            //一个 Z 字周期的字符数
            int charNums = 2 * numRows - 2;
            //周期数 = 总字符数 / 一个周期的字符数，向上取整（最后一个周期可能不完整，算作一个完整周期）
            int tNums = (int) Math.ceil((double) total / charNums);
            //总列数 = 周期数 * 一个周期的列数（numRows - 1）
            int col = tNums * (numRows - 1);
            //dp 数组，用于存储所有字符。 时间复杂度=空间复杂度=O(n*m+m^2)，其中 n 为字符串长度，m 为 numRows
            char[][] dp = new char[col][numRows];
            char[] charArray = s.toCharArray();
            int index = 0;
            for (int i = 0; i < col; i++) {
                for (int j = 0; j < numRows; j++) {
                    //不完整周期，则不再填充
                    if (index >= total) {
                        break;
                    }
                    //完整周期内
                    if (i % (numRows - 1) == 0) {
                        //全字符列
                        dp[i][j] = charArray[index++];
                    } else {
                        //单字符列
                        if (j == (numRows - 1) - i % (numRows - 1)) {
                            dp[i][j] = charArray[index++];
                        } else {
                            dp[i][j] = ' ';
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < numRows; j++) {
                for (int i = 0; i < col; i++) {
                    //不完整周期内会出现数组元素未填充的情况，此时字符值为 '\u0000'，过滤掉即可
                    if (dp[i][j] != ' ' && dp[i][j] != '\u0000') {
                        sb.append(dp[i][j]);
                    }
                }
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}