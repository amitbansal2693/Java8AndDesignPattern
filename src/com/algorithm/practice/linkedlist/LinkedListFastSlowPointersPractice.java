package com.algorithm.practice.linkedlist;

/**
 * ============================================================================
 * PRACTICE: Linked List - Fast & Slow Pointers
 * ============================================================================
 * 
 * This is the PRACTICE version - incomplete methods for you to solve
 * 
 * Complete each method following the hints and approach comments
 * Test your solution against the examples in main()
 * 
 * ============================================================================
 */
public class LinkedListFastSlowPointersPractice {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        System.out.println("========== LINKED LIST FAST & SLOW POINTERS PRACTICE ==========\n");

        // Practice 1: Find Middle of Linked List
        System.out.println("=== PRACTICE 1: Find Middle of Linked List ===");
        Node head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("List: ");
        printList(head1);
        try {
            Node middle = findMiddle(head1);
            System.out.println("Middle element: " + middle.data);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        // Practice 2: Detect Cycle
        System.out.println("\n=== PRACTICE 2: Detect Cycle in Linked List ===");
        Node head2 = createListWithCycle();
        System.out.println("Created list with cycle");
        try {
            boolean hasCycle = hasCycle(head2);
            System.out.println("Has cycle: " + hasCycle);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        // Practice 3: Remove Nth Node from End
        System.out.println("\n=== PRACTICE 3: Remove Nth Node from End ===");
        Node head3 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original list: ");
        printList(head3);
        try {
            head3 = removeNthFromEnd(head3, 2);
            System.out.print("After removing 2nd node from end: ");
            printList(head3);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        // Practice 4: Find cycle entry point
        System.out.println("\n=== PRACTICE 4: Find Cycle Entry Point ===");
        Node head4 = createListWithCycleAtNode(3);
        try {
            int cycleEntry = findCycleEntry(head4);
            System.out.println("Cycle entry value: " + cycleEntry);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }
    }

    /**
     * PRACTICE 1: Find Middle of Linked List
     * 
     * Problem: Find the middle node of a linked list
     * Input: 1→2→3→4→5
     * Expected Output: 3 (middle node)
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Fast & Slow Pointers):
     * 1. Create two pointers: slow and fast
     * 2. Initialize both to head
     * 3. Loop while fast and fast.next are not null:
     *    - Move slow 1 step: slow = slow.next
     *    - Move fast 2 steps: fast = fast.next.next
     * 4. When fast reaches end, slow is at middle
     * 
     * WHY IT WORKS:
     * - fast moves 2x faster than slow
     * - When fast reaches end, slow has covered half the distance
     * - That position is approximately the middle
     * 
     * VISUALIZATION:
     * 1→2→3→4→5
     * S     F      (Step 1: slow=1, fast=3)
     *   S     F    (Step 2: slow=2, fast=5)
     *     S   F→null (Step 3: slow=3, fast=null)
     * Result: slow at 3 ✓
     * 
     * HINT: Check conditions carefully to avoid null pointer exceptions
     * 
     * Time: O(n), Space: O(1)
     */
    public static Node findMiddle(Node head) {
        throw new UnsupportedOperationException(
            "TODO: Find middle node using fast & slow pointers\n" +
            "Hint: slow moves 1 step, fast moves 2 steps"
        );
    }

    /**
     * PRACTICE 2: Detect if Linked List has a Cycle
     * 
     * Problem: Return true if linked list contains a cycle
     * Input: 1→2→3→4→3 (4 points back to 3, creating cycle)
     * Expected Output: true
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Fast & Slow Pointers):
     * 1. Handle edge case: if head is null or has only one node, return false
     * 2. Initialize slow and fast pointers to head
     * 3. Loop:
     *    - Move slow 1 step: slow = slow.next
     *    - Move fast 2 steps: fast = fast.next.next
     *    - Check if slow == fast (pointers meeting means cycle)
     * 4. If fast reaches null (fast.next == null), no cycle
     * 5. If slow == fast, cycle exists
     * 
     * WHY IT WORKS (Floyd's Cycle Detection):
     * - If there's a cycle, fast will eventually "lap" slow
     * - fast moves twice as fast, so it will catch up
     * - If no cycle, fast reaches null first
     * 
     * EXAMPLE with cycle:
     * 1→2→3→4
     *     ↑___↓ (4 points to 3)
     * 
     * Step 1: slow=1, fast=3
     * Step 2: slow=2, fast=4
     * Step 3: slow=3, fast=3 ✓ MEET (cycle detected!)
     * 
     * HINT: Check null conditions before accessing next.next
     * 
     * Time: O(n), Space: O(1)
     */
    public static boolean hasCycle(Node head) {
        throw new UnsupportedOperationException(
            "TODO: Detect cycle using fast & slow pointers\n" +
            "Hint: If they meet at same node, cycle exists"
        );
    }

    /**
     * PRACTICE 3: Remove Nth Node from End of List
     * 
     * Problem: Remove the Nth node from the end and return new head
     * Input: 1→2→3→4→5, N=2
     * Expected Output: 1→2→3→5 (removed 4, which is 2nd from end)
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Two Pointers with Gap):
     * 1. Create dummy node pointing to head (handles removing head edge case)
     * 2. Initialize two pointers: slow and fast, both at dummy
     * 3. Create gap of N nodes:
     *    - Move fast pointer N steps ahead
     * 4. Move both pointers until fast reaches last node:
     *    - While fast.next != null:
     *      * slow = slow.next
     *      * fast = fast.next
     * 5. Remove the node:
     *    - slow.next = slow.next.next
     * 6. Return dummy.next (handles if head was removed)
     * 
     * WHY IT WORKS:
     * - fast is N nodes ahead of slow
     * - When fast reaches last node, slow is at node BEFORE removal point
     * - So slow.next is the node to remove
     * 
     * EXAMPLE for N=2:
     * dummy→1→2→3→4→5
     * S            F    (gap=2)
     * 
     * Move both:
     * dummy→1→2→3→4→5
     *       S        F   (fast at last)
     * 
     * Remove: slow.next = slow.next.next
     * Result: dummy→1→2→3→5
     * 
     * HINT: dummy node helps when removing head node
     * 
     * Time: O(n), Space: O(1)
     */
    public static Node removeNthFromEnd(Node head, int n) {
        throw new UnsupportedOperationException(
            "TODO: Remove Nth node from end\n" +
            "Hint: Use dummy node, create N-node gap between pointers"
        );
    }

    /**
     * PRACTICE 4: Find Entry Point of Cycle
     * 
     * Problem: Find the node where cycle begins
     * Input: 1→2→3→4→5
     *            ↑_____|
     * Cycle starts at node 3 (5 points to 3)
     * Expected Output: 3
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Floyd's Cycle Detection - Two Phase):
     * 
     * PHASE 1: Detect Cycle
     * 1. Use fast & slow pointers to detect if cycle exists
     * 2. If cycle found, they meet at some node
     * 3. If no cycle, return -1
     * 
     * PHASE 2: Find Entry Point
     * 1. Reset slow pointer to head
     * 2. Keep fast at meeting point
     * 3. Move both pointers 1 step at a time
     * 4. When they meet again, that's the cycle entry point
     * 
     * WHY PHASE 2 WORKS (Mathematical Proof):
     * - Slow traveled: D + m (where D=distance to cycle start, m=distance in cycle)
     * - Fast traveled: 2*(D+m) = 2*D + 2*m
     * - Distance from meeting point to entry = D
     * - So moving one step at a time from head and from meeting point,
     *   they meet at cycle entry
     * 
     * TRACE:
     * 1→2→3→4→5
     *     ↑___↓
     * Phase 1 result: slow and fast meet at node 3
     * 
     * Phase 2:
     * slow=1 (from head), fast=3 (meeting point)
     * Move both 1 step: slow=2, fast=4
     * Move both 1 step: slow=3, fast=5
     * Move both 1 step: slow=4, fast=3
     * Move both 1 step: slow=3, fast=4
     * Hmm, they don't seem to meet... Let me reconsider
     * 
     * Actually for 1→2→3→4→...→0, cycle at different spot
     * The mathematical property guarantees they will meet at entry point
     * 
     * HINT: Use hasCycle logic first, then do phase 2
     * 
     * Time: O(n), Space: O(1)
     */
    public static int findCycleEntry(Node head) {
        throw new UnsupportedOperationException(
            "TODO: Find cycle entry point\n" +
            "Hint: Two phases - detect cycle, then reset one pointer to head"
        );
    }

    // ========================================================================
    // Helper Methods
    // ========================================================================

    private static Node createList(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }

    private static Node createListWithCycle() {
        // 1→2→3→4→5→3 (cycle at 3)
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n3; // Creates cycle

        return n1;
    }

    private static Node createListWithCycleAtNode(int cycleAt) {
        // 1→2→3→4→5→cycleAt
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        if (cycleAt == 1) n5.next = n1;
        else if (cycleAt == 2) n5.next = n2;
        else if (cycleAt == 3) n5.next = n3;
        else if (cycleAt == 4) n5.next = n4;

        return n1;
    }

    private static void printList(Node head) {
        Node current = head;
        int count = 0;
        while (current != null && count < 10) {
            System.out.print(current.data + "→");
            current = current.next;
            count++;
        }
        System.out.println("null");
    }
}

