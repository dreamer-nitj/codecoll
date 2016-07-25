   /*
    
    class Node 
       int data;
       Node left;
       Node right;
   */
   int height(Node root) {
       return height(root, 0) - 1;
   }

   int height(Node root, int level) {
       if (root == null) {
           return level;
       }
       
       return max(height(root.left, level + 1), height(root.right, level + 1));
   }

   int max(int u, int v) {
       if (u > v) {
           return u;
       }
       
       return v;
   }
