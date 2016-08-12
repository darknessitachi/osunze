package com.skycity.framework.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.skycity.framework.collection.Treex.TreeNode;


public class Treex<T> implements Iterable<TreeNode<T>>, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private TreeNode<T> root = new TreeNode<T>();
    
    public TreeNode<T> getRoot() {
        return this.root;
    }
    
    public TreeNode<T> getNode(T data) {
        TreeIterator<T> ti = iterator();
        while (ti.hasNext()) {
            TreeNode<T> tn = ti.next();
            if (tn.getData().equals(data)) {
                return tn;
            }
        }
        return null;
    }
    
    public TreeIterator<T> iterator() {
        return new TreeIterator<T>(this.root);
    }
    
    public static <T> TreeIterator<T> iterator(TreeNode<T> node) {
        return new TreeIterator<T>(node);
    }
    
//    public String toString() {
//        return toString(Formatter.DefaultFormatter);
//    }
//    
//    public String toString(Formatter f) {
//        StringBuilder sb = new StringBuilder();
//        TreeIterator<T> ti = iterator();
//        while (ti.hasNext()) {
//            TreeNode<T> tn = ti.nextNode();
//            for (int i = 0; i < tn.getLevel() - 1; i++) {
//            }
//            TreeNode<T> p = tn.getParent();
//            String str = "";
//            while ((p != null) && (!p.isRoot())) {
//                if (p.isLast()) {
//                    str = "  " + str;
//                } else {
//                    str = "│ " + str;
//                }
//                p = p.getParent();
//            }
//            sb.append(str);
//            if (!tn.isRoot()) {
//                if (tn.isLast()) {
//                    sb.append("└─");
//                } else {
//                    sb.append("├─");
//                }
//            }
//            sb.append(f.format(tn.getData()));
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
    
    public ArrayList<TreeNode<T>> toArray() {
        TreeIterator<T> ti = new TreeIterator<T>(this.root);
        ArrayList<TreeNode<T>> arr = new ArrayList<TreeNode<T>>();
        while (ti.hasNext()) {
            arr.add(ti.next());
        }
        return arr;
    }
    
    public static class TreeIterator<T> implements Iterator<Treex.TreeNode<T>>, Iterable<Treex.TreeNode<T>> {
        
        private Treex.TreeNode<T> last;
        
        private Treex.TreeNode<T> next;
        
        private Treex.TreeNode<T> start;
        
        TreeIterator(Treex.TreeNode<T> node) {
            this.start = (this.next = node);
        }
        
        public boolean hasNext() {
            if (this.next == null) {
                return false;
            }
            if ((this.next == this.start) && (this.start.getChildren().size() == 0)) {
                return false;
            }
            if ((this.next != this.start) && (this.next.getLevel() == this.start.getLevel())) {
                return false;
            }
            return true;
        }
        
        public Treex.TreeNode<T> next() {
            if (this.next == null) {
                throw new NoSuchElementException();
            }
            this.last = this.next;
            if (this.next.hasChild()) {
                this.next = (TreeNode<T>) this.next.getChildren().get(0);
            } else {
                while (this.next.getNextSibling() == null) {
                    if (next.parent.isRoot()) {
                        next = null;
                        return last;
                    }
                    next = next.parent;
                }
                next = next.getNextSibling();
            }
            return last;
        }
        
        public Treex.TreeNode<T> nextNode() {
            return next();
        }
        
        public Treex.TreeNode<T> currentNode() {
            return this.next;
        }
        
        public void remove() {
            if (this.last == null) {
                throw new IllegalStateException();
            }
            last.parent.getChildren().remove(last);
            last = null;
        }
        
        public Iterator<Treex.TreeNode<T>> iterator() {
            return this;
        }
    }
    
    public static class TreeNode<T> implements Serializable {
        
        private static final long serialVersionUID = 1L;
        
        private int level;
        
        private T data;
        
        private Treex.TreeNodeList<T> children = new Treex.TreeNodeList<T>();
        
        private TreeNode<T> parent;
        
        private int pos;
        
        public TreeNode<T> addChild(T data) {
            TreeNode<T> tn = new TreeNode<T>();
            this.level += 1;
            tn.data = data;
            tn.parent = this;
            tn.pos = this.children.size();
            this.children.add(tn);
            return tn;
        }
        
        public void removeChild(T data) {
            for (int i = 0; i < this.children.size(); i++) {
                TreeNode<T> child = (TreeNode<T>) this.children.get(i);
                if (child.getData() == null) {
                    if (data == null) {
                        this.children.remove(i);
                        break;
                    }
                } else if (child.getData().equals(data)) {
                    this.children.remove(i);
                    break;
                }
            }
        }
        
        public TreeNode<T> getChild(T data) {
            for (int i = 0; i < this.children.size(); i++) {
                TreeNode<T> child = (TreeNode<T>) this.children.get(i);
                if (child.getData() == null) {
                    if (data == null) {
                        return child;
                    }
                } else if (child.getData().equals(data)) {
                    return child;
                }
            }
            return null;
        }
        
        public TreeNode<T> getPreviousSibling() {
            if (this.pos == 0) {
                return null;
            }
            return (TreeNode<T>) this.parent.getChildren().get(this.pos - 1);
        }
        
        public TreeNode<T> getNextSibling() {
            if ((this.parent == null) || (this.pos == this.parent.getChildren().size() - 1)) {
                return null;
            }
            return (TreeNode<T>) this.parent.getChildren().get(this.pos + 1);
        }
        
        public int getLevel() {
            return this.level;
        }
        
        public boolean isRoot() {
            return this.parent == null;
        }
        
        public boolean isLast() {
            if ((this.parent != null) && (this.pos != this.parent.getChildren().size() - 1)) {
                return false;
            }
            return true;
        }
        
        public boolean hasChild() {
            return this.children.size() != 0;
        }
        
        public TreeNode<T> getParent() {
            return this.parent;
        }
        
        public int getPosition() {
            return this.pos;
        }
        
        public Treex.TreeNodeList<T> getChildren() {
            return this.children;
        }
        
        public T getData() {
            return this.data;
        }
        
        public void setData(T data) {
            this.data = data;
        }
    }
    
    public static class TreeNodeList<T> extends ArrayList<Treex.TreeNode<T>> {
        
        private static final long serialVersionUID = 1L;
        
        public Treex.TreeNode<T> remove(Treex.TreeNode<T> node) {
            int pos = node.getPosition();
            for (int i = pos + 1; i < size(); i++) {
                TreeNode<T> tn = (TreeNode<T>) get(i);
                tn.pos -= 1;
            }
            super.remove(node);
            return node;
        }
        
        public Treex.TreeNode<T> last() {
            int size = size();
            if (size == 0) {
                return null;
            }
            return (TreeNode<T>) get(size - 1);
        }
    }
    
}
