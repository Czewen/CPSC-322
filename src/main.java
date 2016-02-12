import java.math.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class main {

	public static void main(String[] args){
		TreeNode tree = constructNonHeuristicTree();
		System.out.println(tree.node_count);
		System.out.println(tree.leaves.size());
		for(TreeNode node : tree.leaves){
			if(node.consistencyCheck()){
				System.out.println();
				node.printElements();
			}
		}
	}
	
	public static TreeNode constructNonHeuristicTree(){
		LinkedList<TreeNode> frontier = new LinkedList<TreeNode>();
		TreeNode head = new TreeNode();
		head.leaves = new ArrayList<>();
		frontier.add(head);
		while(frontier.size()>0){
			TreeNode curr = frontier.remove();
			if(!curr.hasAllAssigned()){
				if(curr.consistencyCheck()){
					for(int i=1; i<5; i++){
						TreeNode child = curr.copy();
						child.setNextVariable(i);
						curr.addChild(child);
						head.node_count++;
						frontier.add(child);
					}
				}
			}
			else{
				head.leaves.add(curr);
			}
		}
		return head;
	}
	
	
}
