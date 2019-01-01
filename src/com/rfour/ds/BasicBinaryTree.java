package com.rfour.ds;

import java.util.Currency;

public class BasicBinaryTree <X extends Comparable<X>>{
    private Node root;
    private  int size;

    public int size(){
        return size;
    }

    public void add(X item){
        Node node = new Node(item);
        if(root ==null){
            this.root = node;
            System.out.println("Set Root : "  + node.getItem());
            this.size++;
        }
        else{
            insert(this.root, node);
        }
    }

    public boolean contains(X item){
        Node currentNode = getNode(item);
        if(currentNode ==null){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean delete(X item){
        boolean deleted = false;

        if(this.root==null){
            return false;
        }
        //find the node to delete
        Node currentNode = getNode(item);
        if(currentNode != null){
            if(currentNode.getLeft() ==null && currentNode.getRight() ==null){
                unlink(currentNode,null);
                deleted= true;
            }
            else if (currentNode.getLeft() ==null && currentNode.getRight() != null){
                unlink(currentNode, currentNode.getRight());
                deleted = true;
            }
            else if (currentNode.getLeft() !=null && currentNode.getRight() == null){
                unlink(currentNode, currentNode.getLeft());
                deleted = true;
            }
            //The node has both children left/right do a node swap to delete
            else{
                //you can swap out the node with the right most leaf node
                Node child = currentNode.getLeft();
                while (child.getRight() !=null && child.getLeft() != null  ){
                    child = child.getRight();
                }
                //we have the right most leaf node
                child.getParent().setRight(null);  //remove the leaf node from this current position

                child.setLeft(currentNode.getLeft());
                child.setRight(currentNode.getRight());

                unlink(currentNode, child);
                deleted= true;
            }
        }

        if(deleted){
            this.size--;
        }
        return deleted;
    }

    private void unlink(Node currentNode, Node newNode){
        if(currentNode==this.root){
            newNode.setLeft(currentNode.getLeft());
            newNode.setRight(currentNode.getRight());
            this.root = newNode;
        }
        else if (currentNode.getParent().getRight() == currentNode){
            currentNode.getParent().setRight(newNode);
        }
        else{
            currentNode.getParent().setLeft(newNode);
        }

    }

    private Node getNode(X item){
        Node currentNode = this.root;

        while(currentNode != null ){
            int val = item.compareTo(currentNode.getItem());
            if (val ==0){
                return currentNode;
            }
            else if (val <0){
                currentNode = currentNode.getLeft();
            }
            else{
                currentNode = currentNode.getRight();
            }
        }
        return null;
    }

    private void insert (Node parent, Node child){

        //if the child is less than the parent, it goes to the left
        if (child.getItem().compareTo(parent.getItem()) <0){
            //if the left node is null, we've fond our spot
            if(parent.getLeft() ==null){
                parent.setLeft(child);
                child.setParent(parent);
                this.size++;
            }
            //recursive call to find when the left is reached.
            else{
                insert(parent.getLeft(),child);
            }
        }
        else if (child.getItem().compareTo(parent.getItem()) >0){
            //if the right node is null, we've fond our spot
            if(parent.getRight() ==null){
                parent.setRight(child);
                child.setParent(parent);
                this.size++;
            }
            //recursive call to find when the right is reached.
            else{
                insert(parent.getRight(),child);
            }
        }

        //if the parent and child happend to be equal,  we don't do anything
        //data in a binary tree is assumed to be unique and the value already exists in the tree.
    }

    public BasicBinaryTree(){
        this.root = null;
    }

    private class Node{
        private Node left;
        private Node right;
        private Node parent;
        private X item;

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public X getItem() {
            return item;
        }

        public void setItem(X item) {
            this.item = item;
        }

        public Node(X item){
            this.item = item;
            this.left = null;
            this.right = null;
            this.parent = null;

        }
    }


}
