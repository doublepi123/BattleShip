

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * BRTree (红黑树)
 *
 * 1.创建RBTree,定义颜色
 * 2.创建RBNode
 * 3.辅助方法定义：parentOf(node),isRed(node),isBlack(node),setRed(node),setBlack(node),inOrderPrint()
 * 4.左旋方法定义：leftRotate(node)
 * 5.右旋方法定义：rightRotate(node)
 * 6.公有插入方法定义：insert(K key,V value)
 * 7.私有插入方法定义：insert(RBNode node)
 * 8.插入重新平衡方法定义：insertBalance(RBNode node);
 * 9.测试红黑树的正确性
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>, V> {

    /**红色*/
    private static final boolean RED = true;

    /**黑色*/
    private static final boolean BLACK = false;

    private RBNode root;

    /**
     * 获取节点的父节点
     * @param node
     * @return
     */
    private RBNode parentOf(RBNode node){
        return node == null ? null : node.parent;
    }

    /**
     * 获取节点的颜色
     * @param node
     * @return
     */
    private boolean colorOf(RBNode node){
        return node.color;
    }

    /**
     * 节点红色设置成黑色
     * @param node
     * @return
     */
    private void setBlack(RBNode node){
        if (node != null){
            node.color = BLACK;
        }
    }

    /**
     * 节点红色设置成红色
     * @param node
     * @return
     */
    private void setRed(RBNode node){
        if (node != null){
            node.color = RED;
        }
    }

    /**
     * 节点是否是红色
     * @param node
     * @return
     */
    private boolean isRed(RBNode node){
        return node == null ? false : node.color;
    }

    /**
     * 节点是否是黑色
     * @param node
     * @return
     */
    private boolean isBlack(RBNode node){
        return node == null ? false : node.color == BLACK;
    }

    /**
     * 前序遍历
     *           A
     *        |    \
     *       B      C
     *    |   \    |
     *    D    E   F
     * 访问顺序：根节点→左子树→右子树
     * 前序遍历的结果是：A→B→D→E→C→F
     */
    public void preOrderTraversal(){
        System.out.println("    递归：");
        preOrderTraversalRecursive(this.root);
        System.out.println();
        System.out.println("    非递归：");
        preOrderTraversalUnRecursive(this.root);
    }

    /**
     * 前序遍历
     * 递归
     * @param node
     */
    private void preOrderTraversalRecursive(RBNode node){
        if (node != null){
            System.out.print(node.k + "-");
            preOrderTraversalRecursive(node.left);
            preOrderTraversalRecursive(node.right);
        }
    }

    /**
     * 前序遍历
     * 非递归
     * @param node
     */
    private void preOrderTraversalUnRecursive(RBNode node){
        if (node == null) return;
        Stack<RBNode> stack = new Stack<RBNode>();
        stack.push(node);
        while (!stack.isEmpty()){
            node = stack.pop();
            System.out.print(node.k + "-");
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
    }

    /**
     * 中序遍历
     *           A
     *        |    \
     *       B      C
     *    |   \    |
     *    D    E   F
     * 访问顺序：左子树→根节点→右子树
     * 前序遍历的结果是：D→B→E→A→F→C
     *
     * 一般遍历用到的都是中序遍历，因为中序遍历是有序的。
     */
    public void inOrderTraversal(){
        System.out.println("    递归：");
        inOrderTraversalRecursive(this.root);
        System.out.println();
        System.out.println("    非递归：");
        inOrderTraversalUnRecursive(this.root);
    }

    /**
     * 中序遍历递归的写法
     * @param node
     */
    private void inOrderTraversalRecursive(RBNode node) {
        if (node != null){
            inOrderTraversalRecursive(node.left);
            System.out.print(node.k + "-");
            inOrderTraversalRecursive(node.right);
        }
    }

    /**
     * 中序遍历非递归的写法
     * @param node
     */
    private void inOrderTraversalUnRecursive(RBNode node) {
        Stack<RBNode> stack = new Stack();
        while (node != null || !stack.isEmpty()){
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.k + "-");
                node = node.right;
            }
        }
    }

    /**
     * 后序遍历
     *           A
     *        |    \
     *       B      C
     *    |   \    |
     *    D    E   F
     * 访问顺序：左子树→右子树→根节点
     * 后序遍历的结果是：D→E→B→F→C→A
     */
    public void postOrderTraversal(){
        System.out.println("    递归：");
        postOrderTraversalRecursive(this.root);
        System.out.println();
        System.out.println("    非递归1：");
        postOrderTraversalUnRecursive(this.root);
        System.out.println();
        System.out.println("    非递归2：");
        postOrderTraversalNoRecursive(this.root);
    }

    /**
     * 后序遍历递归写法
     */
    private void postOrderTraversalRecursive(RBNode node){
        if (node != null){
            postOrderTraversalRecursive(node.left);
            postOrderTraversalRecursive(node.right);
            System.out.print(node.k + "-");
        }
    }

    /**
     * 后序遍历非递归写法
     */
    private void postOrderTraversalUnRecursive(RBNode node){
        Stack<RBNode> stack1 = new Stack<RBNode>();
        Stack<RBNode> stack2 = new Stack<RBNode>();
        stack1.push(node);
        while (!stack1.isEmpty()){
            node = stack1.pop();
            stack2.push(node);
            if (node.left != null){
                stack1.push(node.left);
            }
            if (node.right != null){
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().k + "-");
        }
    }

    /**
     * 非递归的另外一种写法
     * @param node
     */
    private void postOrderTraversalNoRecursive(RBNode node) {
        if (node != null){
            Stack<RBNode> stack = new Stack();
            stack.push(node);
            RBNode tmp;
            while (!stack.isEmpty()) {
                tmp = stack.peek();
                if (tmp.left != null && node != tmp.left && node != tmp.right) {
                    stack.push(tmp.left);
                } else if (tmp.right != null && node != tmp.right) {
                    stack.push(tmp.right);
                } else {
                    System.out.print(stack.pop().k + "-");
                    node = tmp;
                }
            }
        }
    }

    /**
     * BFS(广度优先搜索)
     *           A
     *        |    \
     *       B      C
     *     |   \   |
     *    D    E   F
     * 访问顺序：先访问上一层，在访问下一层，一层一层的往下访问
     * BFS遍历的结果：A→B→C→D→E→F
     * @param node
     */
    public void breadthFirstSearch(){
        System.out.println("    递归：");
        breadthFirstSearchRecursive(this.root);
        System.out.println();
        System.out.println("    非递归：");
        breadthFirstSearchUnRecursive(this.root);
    }

    /**
     * 广度优先搜索
     * 递归写法
     * @param node
     */
    private void breadthFirstSearchRecursive(RBNode node){
        int depth = depth(node);
        for (int i = 0; i < depth; i++){
            printByLevel(node,i);
        }
    }

    /**
     * 按层打印树
     * @param node
     * @param level
     */
    private void printByLevel(RBNode node, int level) {
        if (node != null){
            if (level == 0){
                System.out.print(node.k + "-");
            }else{
                printByLevel(node.left,level - 1);
                printByLevel(node.right,level - 1);
            }
        }
    }

    /**
     * 获取树的深度
     * @param node
     * @return
     */
    private int depth(RBNode node) {
        if (node == null) return 0;
        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);
        return Math.max(leftDepth,rightDepth) + 1;
    }

    /**
     * 广度优先搜索
     * 非递归写法
     * @param node
     */
    private void breadthFirstSearchUnRecursive(RBNode node){
        if (node != null){
            LinkedList<RBNode> list = new LinkedList();
            list.add(node);
            RBNode tmp;
            while(!list.isEmpty()){
                tmp = list.poll();
                System.out.print(tmp.k + "-");
                if (tmp.left != null){
                    list.add(tmp.left);
                }
                if (tmp.right != null){
                    list.add(tmp.right);
                }
            }
        }
    }

    /**
     * DFS(深度优先搜索))
     *           A
     *        |    \
     *       B      C
     *     |   \   |
     *    D    E   F
     * 访问顺序：先访根节点，然后左结点，一直往下，直到最左结点没有子节点的时候然后往上退一步到父节点，
     *          然后父节点的右子节点在重复上面步骤……
     * BFS遍历的结果：A→B→D→E→C→F
     * @param node
     */
    public void depthFirstSearch(){
        System.out.println("    递归：");
        depthFirstSearchRecursive(this.root);
        System.out.println();
        System.out.println("    非递归：");
        depthFirstSearchUnRecursive(this.root);
    }

    /**
     * 深度优先搜索
     * 递归写法
     * @param node
     */
    private void depthFirstSearchRecursive(RBNode node) {
        if (node != null){
            System.out.print(node.k + "-");
            depthFirstSearchRecursive(node.left);
            depthFirstSearchRecursive(node.right);
        }
    }

    /**
     * 深度优先搜索
     * 非递归写法
     * @param node
     */
    private void depthFirstSearchUnRecursive(RBNode node) {
        Stack<RBNode> stack = new Stack<RBNode>();
        stack.push(node);
        while(!stack.isEmpty()){
            node = stack.pop();
            System.out.print(node.k + "-");
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
    }

    public void printTree(){
        printTree(this.root);
    }

    public void printTree(RBNode node){
        if (node == null)
            return;
        LinkedList<RBNode> list = new LinkedList<RBNode>();//链表，这里我们可以把它看做队列
        list.add(node);//相当于把数据加入到队列尾部
        boolean line = false;
        while (!list.isEmpty()) {
            RBNode n = list.poll();//poll方法相当于移除队列头部的元素
            if (line){
                if (n.color == true){
                    System.out.print(n.k + " - RED      ");
                }else {
                    System.out.print(n.k + " - BLACK        ");
                }
            }else{
                if (n.color == true){
                    System.out.println(n.k + " - RED        ");
                }else {
                    System.out.println(n.k + " - BLACK      ");
                }
            }
            if (n.left != null)
                list.add(n.left);
            if (n.right != null){
                list.add(n.right);
            }
            if (n.left != null && n.right != null){
                line = true;
            }else{
                line = false;
            }
        }
    }

    /**
     * 左旋方法
     * 示意图：
     *       P                              P
     *       |                              |
     *       x                              y
     *    |    \        ------>          |    \
     *   xl     y                       x      ry
     *        |   \                   |   \
     *       ly    ry                xl    ly
     * 1.将y的左子节点(ly)的父节点更新为x(ly要挂在x的右边)，并将ly的父节点更新为x(双向关联的)
     * 2.如果x父节点不为空，将y的父节点更新成x的父节点，如果x是x父节点的左节点，则更新x父节点
     *   的左子节点为y，否则(不是左子节点就是右子节点)则更新x的父节点的右子节点为y(记住节点之
     *   间的关系都是双向的，所以更新的时候要双向更新)
     * 3.将x的父节点更新为y，将y的左子节点更新为x.
     * @param node 以node为当前节点左旋
     */
    private void leftRotate(RBNode x){
        RBNode y = x.right;
        //第一步
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;
        //第二步
        y.parent = x.parent;
        if (x.parent != null){
            if (x == x.parent.left)
                x.parent.left = y;
            else
                x.parent.right = y;
        }else{
            this.root = y;
        }
        //第三步
        x.parent = y;
        y.left = x;
    }

    /**
     * 右旋方法
     * 示意图：
     *              P                               P
     *              |                               |
     *              y                               x
     *           |    \         ------>          |    \
     *          x      ry                       xl     y
     *        |   \                                  |   \
     *      xl    ly                                ly    ry
     * 1.将x的右子节点(ly)的父节点更新为y(ly要挂在y的左边)，并将ly的父节点更新为y(双向关联的)
     * 2.如果y父节点不为空，将x的父节点更新成y的父节点，如果y是y父节点的左节点，则更新y父节点
     *   的左子节点为x，否则(不是左子节点就是右子节点)则更新y的父节点的右子节点为x(记住节点之
     *   间的关系都是双向的，所以更新的时候要双向更新)
     * 3.将y的父节点更新为x，将x的右子节点更新为y.
     * @param node 以node为当前节点左旋
     */
    private void rightRotate(RBNode y){
        //第一步
        RBNode x = y.left;
        y.left = x.right;
        if (x.right != null)
            x.right.parent = y;
        //第二步
        x.parent = y.parent;
        if (y.parent != null){
            if (y == y.parent.left)
                y.parent.left = x;
            else
                y.parent.right = x;
        }else {
            this.root = x;
        }
        //第三步
        y.parent = x;
        x.right = y;
    }

    /**
     * 从指定节点开始搜索指定k对应的节点
     * @param node
     * @param k
     * @return
     */
    private RBNode search(RBNode node, K k) {
        if (node == null) return null;
        int cmp;
        if ((cmp = node.k.compareTo(k)) == 0){
            return node;
        }else if (cmp > 0){
            node = search(node.left, k);
        }else{
            node = search(node.right, k);
        }
        return node;
    }

    /**
     * 根据K,V插入
     * @param k 插入的键
     * @param v 插入的值
     * @return 如果替换，则返回原值。否则返回null
     */
    public V insert(K k,V v){
        return insert(new RBNode(k, v,RED));
    }

    /**
     * 根据Node插入
     * @param node 插入节点
     * @return 如果替换，则返回原值。否则返回null
     */
    private V insert(RBNode node){
        RBNode parent = null;
        RBNode x = this.root;
        while (x != null){//从根节点开始遍历，x!=null,则说明没找到叶节点。
            parent = x;//因为循环跳出后x为叶节点null,所以提前保存期父节点。
            int cmp = node.k.compareTo(x.k);//当前节点的k与x的k比较
            if (cmp > 0){//大于0，则将x.right赋值给x继续遍历
                x = x.right;
            }else if (cmp == 0){//等于0说明找到了相同的key，直接替换原值
                Object old = x.v;
                x.v = node.v;
                return (V)old;
            }else {
                x = x.left;//小于0，则将x.left赋值给x继续遍历
            }
        }
        node.parent = parent;//跳出循环后说明找到了，存放的位置（对应叶节点）。将其parent赋值给node的parent。
        if (parent != null){
            if (parent.k.compareTo(node.k) > 0){//比较parent.key与node的key,大于0则说明节点存放于父节点的左子节点
                parent.left = node;
            }else {
                parent.right = node;//否则存放于右子节点
            }
        }else{
            this.root = node;
        }
        //至此插入完成

        //调整红黑树，使其重新平衡
        insertReBalance(node);
        return null;
    }

    /**
     * 插入后重新平衡红黑树
     * 分情况：
     *        1.红黑树为空树(root == null),第一次插入直接将节点染色为黑色，赋值给根节点。
     *        2.插入节点的key已经存在，直接替换原值。不需要处理。
     *        3.插入节点的父节点为黑色，因为插入路径中黑色节点没有变化，红黑树依然平衡，不需要处理。
     *
     *        第四种情况需要我们自己处理：
     *        4.插入节点的父节点为红色。
     *              4.1.叔叔节点存在，且为红色（父-叔 双红）。将父节点染色为黑色，将爷爷节点染色为红色。再以爷爷节点为当前节点处理。
     *              4.2.叔叔节点不存在或者为黑色，且父节点为爷爷节点的左子节点。
     *                  4.2.1.插入节点为父节点的左子节点。（LL情况），将父节点染为黑色，将爷爷节点染为红色，然后以爷爷节点右旋，就完成了。
     *                  4.2.2.插入节点为父节点的右子节点。（LR情况），以父节点为当前节点左旋，得到LL情况（4.2.1）。然后按照4.2.1处理
     *              4.3.叔叔节点不存在或者为黑色，且父节点为爷爷节点的右子节点。
     *                  4.3.1.插入节点为父节点的右子节点。（RR情况），将父节点染为黑色，将爷爷节点染为红色，然后以爷爷节点左旋，就完成了。
     *                  4.3.2.插入节点为父节点的左子节点。（RL情况），以父节点为当前节点右旋，得到RR情况（4.3.1）。然后按照4.3.1处理
     * @param node
     */
    private void insertReBalance(RBNode node){
        setBlack(this.root);
        RBNode parent = parentOf(node);
        RBNode grandParent = parentOf(parent);
        if (parent != null && isRed(parent)){//如果父节点为红色，父节点为红色时一定存在爷爷节点，因为根节点一定为黑色。
            RBNode uncle = null;
            if (parent == grandParent.left){//父节点是爷爷节点的左节点
                uncle = grandParent.right;//则叔叔节点为爷爷节点的右节点
                if (uncle != null && isRed(uncle)){//4.1.叔叔节点存在，且为红色（父-叔 双红）。将父节点染色为黑色，将爷爷节点染色为红色。再以爷爷节点为当前节点处理。
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(grandParent);
                    insertReBalance(grandParent);
                    return;
                }
                if (uncle == null || isBlack(uncle)){//4.2.叔叔节点不存在或者为黑色
                    if (node == parent.left){//4.2.1.插入节点为父节点的左子节点(LL),将父节点染为黑色，将爷爷节点染为红色，然后以爷爷节点右旋，就完成了。
                        setBlack(parent);
                        setRed(grandParent);
                        rightRotate(grandParent);
                    }
                    if (node == parent.right){//4.2.2.插入节点为父节点的右子节点(LR),以父节点为当前节点左旋，得到LL情况（4.2.1）。然后按照4.2.1处理
                        leftRotate(parent);
                        insertReBalance(parent);
                        return;
                    }
                }
            }else{//否则父节点为爷爷节点的右子节点
                uncle = grandParent.left;//则叔叔节点为爷爷节点的左子节点
                if (uncle != null && isRed(uncle)){//4.1.叔叔节点存在，且为红色（父-叔 双红）。将父节点染色为黑色，将爷爷节点染色为红色。再以爷爷节点为当前节点处理。
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(grandParent);
                    insertReBalance(grandParent);
                    return;
                }
                if (uncle == null || isBlack(uncle)){//4.3.叔叔节点不存在或者为黑色，且父节点为爷爷节点的右子节点。
                    if (node == parent.right){//4.3.1.插入节点为父节点的右子节点。（RR情况），将父节点染为黑色，将爷爷节点染为红色，然后以爷爷节点左旋，就完成了。
                        setBlack(parent);
                        setRed(grandParent);
                        leftRotate(grandParent);
                        return;
                    }
                    if (node == parent.left){//4.3.2.插入节点为父节点的左子节点。（RL情况），以父节点为当前节点右旋，得到RR情况（4.3.1）。然后按照4.3.1处理
                        rightRotate(parent);
                        insertReBalance(parent);
                        return;
                    }
                }
            }
        }
    }

    /**
     * 树节点
     * @param <K>
     * @param <V>
     */
    static class RBNode<K extends Comparable<K>, V>{

        private RBNode parent;

        private RBNode left;

        private RBNode right;

        private boolean color;

        private K k;

        private V v;

        public RBNode() {}

        public RBNode(K k, V v, boolean color){
            this.k = k;
            this.v = v;
            this.color = color;
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K k, V v) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.k = k;
            this.v = v;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }
    }
}

