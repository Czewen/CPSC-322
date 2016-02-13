import java.util.ArrayList;


public class TreeNodeHeuristicVersion {
	ArrayList<TreeNodeHeuristicVersion> children;
	int node_count = 0;
	int a = -1;
	int b = -1;
	int c = -1;
	int d = -1;
	int e = -1;
	int f = -1;
	int g = -1;
	int h = -1;
	int[] variables = new int[8];
	ArrayList<TreeNodeHeuristicVersion> leaves;
	ArrayList<TreeNodeHeuristicVersion> solutions;
	String print = "";
	int last_assigned_index;
	int constraint_check_fail = 0;
	TreeNodeHeuristicVersion parent;
	
	public TreeNodeHeuristicVersion() {
		children = new ArrayList<TreeNodeHeuristicVersion>();
		for (int i = 0; i < 8; i++) {
			variables[i] = -1;
		}
	}

	public void addChild(TreeNodeHeuristicVersion child) {
		children.add(child);
	}

	public void deleteChild(TreeNodeHeuristicVersion child) {
		children.remove(child);
	}

	public void setA(int num) {
		variables[6] = num;
		a = num;
	}

	public void setB(int num) {
		variables[5] = num;
		b = num;
	}

	public void setC(int num) {
		variables[1] = num;
		c = num;
	}

	public void setD(int num) {
		variables[2] = num;
		d = num;
	}

	public void setE(int num) {
		variables[7] = num;
		e = num;
	}

	public void setF(int num) {
		variables[4] = num;
		f = num;
	}

	public void setG(int num) {
		variables[3] = num;
		g = num;
	}

	public void setH(int num) {
		variables[0] = num;
		h = num;
	}

	public boolean hasAllAssigned() {
		for (int i = 0; i < variables.length; i++) {
			if (variables[i] < 0)
				return false;
		}
		return true;
	}

	public boolean constraintCheckH(){
		if(h>0){
			if(e>0 && e== h-2)
				return false;
			if(f>0 && f==h)
				return false;
			if(d>0 && d==h)
				return false;
			if(c>0 && Math.abs(h-c)%2!=0)
				return false;
			if(g>0 && g >= h)
				return false;
			if(a>0 && a>h)
				return false;
		}
		return true;
	}
	
	public boolean constraintCheckC(){
		if(c>0){
			if(f>0 && f==c)
				return false;
			if(e>0 && e==c)
				return false;
			if(d>0 && d==c)
				return false;
			if(g>0 && Math.abs(g-c)!=1)
				return false;	
		}	
		return true;
	}
	
	public boolean constraintCheckD(){
		if(d>0){
			if(f>0 && f==d)
				return false;
			if(e>0 && e>=(d-1))
				return false;
			if(g>0 && d<=g)
				return false;		
		}
		return true;
	}
	
	public boolean constraintCheckG(){
		if(g>0){
			if(f>0 && f==g)
				return false;
			if(a>0 && a<=g)
				return false;
		}
		return true;
	}
	
	public boolean constraintCheckF(){
		if(f>0){
			if(e>0 && Math.abs(e-f)%2==0)
				return false;
			if(b>0 && Math.abs(f-b)!=1)
				return false;
		}
		return true;
	}

	public boolean consistencyCheck() {
		if (constraintCheckH() == false)
			return false;
		if (constraintCheckC() == false)
			return false;
		if (constraintCheckD() == false)
			return false;
		if (constraintCheckG() == false)
			return false;
		if (constraintCheckF() == false)
			return false;
		return true;
	}
	

	public TreeNodeHeuristicVersion copy() {
		TreeNodeHeuristicVersion copy = new TreeNodeHeuristicVersion();
		for (int i = 0; i < 8; i++) {
			copy.variables[i] = variables[i];
		}
		copy.setA(a);
		copy.setB(b);
		copy.setC(c);
		copy.setD(d);
		copy.setE(e);
		copy.setF(f);
		copy.setG(g);
		copy.setH(h);
		return copy;
	}

	public void setNextVariable(int num) {
		int index = 0;
		while (variables[index] != -1 && index < 8) {
			index++;
		}
		switch (index) {
		case 0:
			setH(num);
			break;
		case 1:
			setC(num);
			break;
		case 2:
			setD(num);
			break;
		case 3:
			setG(num);
			break;
		case 4:
			setF(num);
			break;
		case 5:
			setB(num);
			break;
		case 6:
			setA(num);
			break;
		case 7:
			setE(num);
			break;
		}
		last_assigned_index = index;
	}
	
	public void setParent(TreeNodeHeuristicVersion node){
		this.parent = node;
	}
	
	public void printElements() {
		int parent_last_index_assigned = parent.last_assigned_index;
		for(int i=0; i<parent_last_index_assigned+1; i++){
			print+= "     ";
		}
		for(int i=parent.last_assigned_index;i<last_assigned_index+1; i++){
			if(variables[i]>0){
				switch(i){
				case 0:
					print += "H: "+h+" ";
					break;
				case 1:
					print += "C: "+c+" ";
					break;
				case 2:
					print += "D: "+d+" ";
					break;
				case 3:
					print += "G: "+g+" ";
					break;
				case 4:
					print += "F: "+f+" ";
					break;
				case 5:
					print += "B: "+b+" ";
					break;
				case 6:
					print += "A: "+a+" ";
					break;
				case 7:
					print += "E: "+e+" ";
					break;
				}
			}
		}
		if(consistencyCheck() && hasAllAssigned()){
			print += "solution";
		}
		else if(!consistencyCheck()){
			print += "failure";
		}
		System.out.println(print);
	}
	
	public boolean solutionCheck(TreeNode node){
		if(a != node.a)
			return false;
		if(b != node.b)
			return false;
		if(c != node.c)
			return false;
		if(d != node.d)
			return false;
		if(e != node.e)
			return false;
		if(f != node.f)
			return false;
		if(g != node.g)
			return false;
		if(h != node.h)
			return false;
		return true;
	}
	
	public String toString(){
		return "A: "+a+" B: " +b+" C: " +c+" D: " +d+ " E: " +e+ " F: "+f+ " G: "+g+" H: "+h;
	}
	
}
