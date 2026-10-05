package com.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Btree {

	private static final int MIN_DEGREE = 3;

	private Node root;
	private int nextNodeId = 1;

	private static final class Node {
		final int nodeId;
		final String dbTablePointer;
		final List<Integer> keys = new ArrayList<>();
		final List<Node> children = new ArrayList<>();
		boolean leaf;

		Node(int nodeId, boolean leaf) {
			this.nodeId = nodeId;
			this.leaf = leaf;
			this.dbTablePointer = "db://btree_nodes/node-" + nodeId;
		}
	}

	public Btree() {
		root = new Node(nextNodeId++, true);
	}

	public void insert(int key) {
		Node currentRoot = root;
		if (currentRoot.keys.size() == 2 * MIN_DEGREE - 1) {
			Node newRoot = new Node(nextNodeId++, false);
			newRoot.children.add(currentRoot);
			splitChild(newRoot, 0, currentRoot);
			root = newRoot;
			insertNonFull(newRoot, key);
			return;
		}

		insertNonFull(currentRoot, key);
	}

	private void insertNonFull(Node node, int key) {
		int i = node.keys.size() - 1;

		if (node.leaf) {
			node.keys.add(0);
			while (i >= 0 && key < node.keys.get(i)) {
				node.keys.set(i + 1, node.keys.get(i));
				i--;
			}
			node.keys.set(i + 1, key);
			return;
		}

		while (i >= 0 && key < node.keys.get(i)) {
			i--;
		}
		i++;

		Node child = node.children.get(i);
		if (child.keys.size() == 2 * MIN_DEGREE - 1) {
			splitChild(node, i, child);
			if (key > node.keys.get(i)) {
				i++;
			}
		}
		insertNonFull(node.children.get(i), key);
	}

	private void splitChild(Node parent, int childIndex, Node fullChild) {
		Node sibling = new Node(nextNodeId++, fullChild.leaf);

		int middleKey = fullChild.keys.get(MIN_DEGREE - 1);

		for (int j = MIN_DEGREE; j < fullChild.keys.size(); j++) {
			sibling.keys.add(fullChild.keys.get(j));
		}

		if (!fullChild.leaf) {
			for (int j = MIN_DEGREE; j < fullChild.children.size(); j++) {
				sibling.children.add(fullChild.children.get(j));
			}
		}

		while (fullChild.keys.size() > MIN_DEGREE - 1) {
			fullChild.keys.remove(fullChild.keys.size() - 1);
		}

		if (!fullChild.leaf) {
			while (fullChild.children.size() > MIN_DEGREE) {
				fullChild.children.remove(fullChild.children.size() - 1);
			}
		}

		parent.children.add(childIndex + 1, sibling);
		parent.keys.add(childIndex, middleKey);
	}

	public void printTree() {
		System.out.println("B-tree built from the first 100 values");
		System.out.println("Top-to-bottom tree view with DB-table-style pointers:");
		Map<Integer, List<Node>> levels = new HashMap<>();
		collectLevels(root, 0, levels);
		for (int depth = 0; levels.containsKey(depth); depth++) {
			String indent = "  ".repeat(Math.max(0, levels.size() - depth - 1));
			StringBuilder line = new StringBuilder(indent);
			List<Node> nodesAtLevel = levels.get(depth);
			for (int i = 0; i < nodesAtLevel.size(); i++) {
				Node node = nodesAtLevel.get(i);
				if (i > 0) {
					line.append("   ");
				}
				line.append("[")
						.append("node-").append(node.nodeId)
						.append(" keys=").append(node.keys)
						.append(" ptr=").append(node.dbTablePointer)
						.append("]");
			}
			System.out.println(line);
		}
	}

	private void collectLevels(Node node, int depth, Map<Integer, List<Node>> levels) {
		levels.computeIfAbsent(depth, ignored -> new ArrayList<>()).add(node);
		for (Node child : node.children) {
			collectLevels(child, depth + 1, levels);
		}
	}

	public static void main(String[] args) {
		Btree btree = new Btree();

		for (int value = 1; value <= 100; value++) {
			btree.insert(value);
		}

		btree.printTree();
	}
}
