import java.util.ArrayList;

public class TreeNode {

	ArrayList<TreeNode> children;
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
	ArrayList<TreeNode> leaves;

	public TreeNode() {
		children = new ArrayList<TreeNode>();
		for (int i = 0; i < 8; i++) {
			variables[i] = -1;
		}
	}

	public void addChild(TreeNode child) {
		children.add(child);
	}

	public void deleteChild(TreeNode child) {
		children.remove(child);
	}

	public void setA(int num) {
		variables[0] = num;
		//System.out.println("abcd: " + variables[0]);
		a = num;
	}

	public void setB(int num) {
		variables[1] = num;
		b = num;
	}

	public void setC(int num) {
		variables[2] = num;
		c = num;
	}

	public void setD(int num) {
		variables[3] = num;
		d = num;
	}

	public void setE(int num) {
		variables[4] = num;
		e = num;
	}

	public void setF(int num) {
		variables[5] = num;
		f = num;
	}

	public void setG(int num) {
		variables[6] = num;
		g = num;
	}

	public void setH(int num) {
		variables[7] = num;
		h = num;
	}

	public boolean hasAllAssigned() {
		for (int i = 0; i < variables.length; i++) {
			if (variables[i] < 0)
				return false;
		}
		return true;
	}

	public boolean constraintCheckA() {
		if (g > 0 && a <= g) {
			return false;
		}
		if (h > 0 && h > a) {
			return false;
		}
		return true;
	}

	public boolean constraintCheckB() {
		if (f > 0 && Math.abs(f - b) != 1)
			return false;
		return true;
	}

	public boolean constraintCheckC() {
		if (g > 0 && Math.abs(g - c) != -1)
			return false;
		if (h > 0 && Math.abs(h - c) % 2 != 0)
			return false;
		if (d > 0 && d == c)
			return false;
		if (e > 0 && e == c)
			return false;
		if (f > 0 && c == f)
			return false;
		return true;
	}

	public boolean constraintCheckD() {
		if (h > 0 && h == d)
			return false;
		if (g > 0 && d <= g)
			return false;
		if (e > 0 && e >= (d - 1))
			return false;
		if (f > 0 && d == f)
			return false;
		return true;
	}

	public boolean constraintCheckE() {
		if (h > 0 && e == (h - 2))
			return false;
		if (f > 0 && Math.abs(e - f) % 2 == 0)
			return false;
		return true;
	}

	public boolean constraintCheckF() {
		if (g > 0 && g == f)
			return false;
		if (h > 0 && h == f)
			return false;
		return true;
	}

	public boolean constraintCheckG() {
		if (h > 0 && g >= h)
			return false;
		return true;
	}

	public boolean consistencyCheck() {
		if (constraintCheckA() == false)
			return false;
		if (constraintCheckB() == false)
			return false;
		if (constraintCheckC() == false)
			return false;
		if (constraintCheckD() == false)
			return false;
		if (constraintCheckE() == false)
			return false;
		if (constraintCheckF() == false)
			return false;
		if (constraintCheckG() == false)
			return false;
		return true;
	}

	public TreeNode copy() {
		TreeNode copy = new TreeNode();
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
			setA(num);
			break;
		case 1:
			setB(num);
			break;
		case 2:
			setC(num);
			break;
		case 3:
			setD(num);
			break;
		case 4:
			setE(num);
			break;
		case 5:
			setF(num);
			break;
		case 6:
			setG(num);
			break;
		case 7:
			setH(num);
			break;
		}
	}

	public void printElements() {
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		System.out.println("d: " + d);
		System.out.println("e: " + e);
		System.out.println("f: " + f);
		System.out.println("g: " + g);
		System.out.println("h: " + h);
	}

}
