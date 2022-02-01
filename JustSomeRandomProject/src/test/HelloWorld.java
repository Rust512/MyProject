package test;

class BinarySearchTree {
	class Node{
		int val;
		Node right, left;
		
		Node(int val){
			this.val = val;
			this.right = null;
			this.left = null;
		}
	}
	
	public Node root;
	
	BinarySearchTree(){
		this.root = null;
	}
	
	Node insert_(Node node, int val) {
		if(node == null) {
			node = new Node(val);
		}
		if(val < node.val) {
			node.left = this.insert_(node.left, val);
		}
		else if(val > node.val) {
			node.right = this.insert_(node.right, val);
		}
		return node;
	}
	
	void insert(int val) {
		root = this.insert_(root, val);
	}
	
	void Inorder_(Node node) {
		if(node == null) {
			return;
		}
		Inorder_(node.left);
		System.out.print(node.val + ' ');
		Inorder_(node.right);
	}
	
	void Inorder() {
		this.Inorder_(this.root);
	}
}

public class HelloWorld {

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.insert(10);
		tree.insert(20);
		tree.insert(5);
		tree.insert(12);
		
		tree.Inorder();
	}
}
