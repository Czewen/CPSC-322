import java.math.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class main {

	public static void main(String[] args){
		TreeNode tree = constructNonHeuristicTree();
		System.out.println("Solutions: ");
		for(TreeNode solution : tree.solutions){
			System.out.println(solution.toString());
		}
		System.out.println("Failed consistency checks: "+tree.constraint_check_fail);
	}
	 //CODE FOR QUESTION 2A
	public static TreeNode constructNonHeuristicTree(){
		Stack<TreeNode> frontier = new Stack<TreeNode>();
		TreeNode root = new TreeNode();
		root.leaves = new ArrayList<>();
		root.solutions = new ArrayList<>();
		frontier.add(root);
		while(frontier.size()>0){
			TreeNode curr = frontier.pop();
			if(!curr.hasAllAssigned()){
				if(curr.consistencyCheck()){
					for(int i=1; i<5; i++){
						TreeNode child = curr.copy();
						child.setNextVariable(i);
						curr.addChild(child);
						child.setParent(curr);
						root.node_count++;
						frontier.add(child);
					}
				}
			}
			else{
				root.leaves.add(curr);
				if(curr.consistencyCheck()){
					root.solutions.add(curr);
				}
			}
			if(curr!=root){
				curr.printElements();
				if(!curr.consistencyCheck())
					root.constraint_check_fail ++ ;
			}
		}
		return root;
	}
	
	//CODE FOR QUESTION 2B
	public static TreeNodeHeuristicVersion constructHeuristicTree(){
		Stack<TreeNodeHeuristicVersion> frontier = new Stack<>();
		TreeNodeHeuristicVersion root = new TreeNodeHeuristicVersion();
		root.leaves = new ArrayList<>();
		root.solutions = new ArrayList<>();
		frontier.add(root);
		while(frontier.size()>0){
			TreeNodeHeuristicVersion curr = frontier.pop();
			if(!curr.hasAllAssigned()){
				if(curr.consistencyCheck()){
					for(int i=1; i<5; i++){
						TreeNodeHeuristicVersion child = curr.copy();
						child.setNextVariable(i);
						curr.addChild(child);
						child.setParent(curr);
						root.node_count++;
						frontier.add(child);
					}
				}
			}
			else{
				root.leaves.add(curr);
				if(curr.consistencyCheck()){
					root.solutions.add(curr);
				}
			}
			if(curr!=root){
				curr.printElements();
				if(!curr.consistencyCheck())
					root.constraint_check_fail ++ ;
			}
		}
		return root;
	}
	
}
