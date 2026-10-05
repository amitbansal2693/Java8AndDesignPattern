package com.algorithm.linkedlist;

/**
 * ============================================================================
 * PATTERN: Linked List - Fast & Slow Pointers
 * ============================================================================
 * 
 * PATTERN IDENTIFICATION:
 * - Problems about finding middle of linked list
 * - Detecting cycles in linked list
 * - Finding cycle entry point
 * - Relative positioning in linked list
 * 
 * WHEN TO USE FAST & SLOW POINTERS:
 * ✓ "Find middle of linked list"
 * ✓ "Detect if linked list has a cycle"
 * ✓ "Find entry point of cycle"
 * ✓ "Remove Nth node from end"
 * 
 * KEY INSIGHT:
 * - Move slow pointer 1 step per iteration
 * - Move fast pointer 2 steps per iteration (or k steps)
 * - They will eventually meet if there's a cycle
 * - Difference in speeds creates relative positioning
 * 
 * APPROACH EXAMPLES:
 * ┌─ Finding Middle
 * │  → When fast reaches end, slow is at middle
 * │
 * ├─ Detecting Cycle
 * │  → If they meet at some point, cycle exists
 * │
 * └─ Finding Cycle Entry
 *    → After meeting, reset one pointer to head
 *    → Move both one step at a time
 *    → They meet at cycle entry
 * 
 * ============================================================================
 */
public class LinkedListFastSlowPointers {

    // Simple Node class for demonstration
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        // Example 1: Find Middle of Linked List
        System.out.println("=== EXAMPLE 1: Find Middle of Linked List ===");
        Node head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("List: ");
        printList(head1);
        
        Node middle = findMiddle(head1);
        System.out.println("Middle element: " + middle.data);

        // Example 2: Detect Cycle
        System.out.println("\n=== EXAMPLE 2: Detect Cycle in Linked List ===");
        Node head2 = createListWithCycle();
        System.out.println("Created list with cycle");
        boolean hasCycle = hasCycle(head2);
        System.out.println("Has cycle: " + hasCycle);

        // Example 3: Remove Nth Node from End
        System.out.println("\n=== EXAMPLE 3: Remove Nth Node from End ===");
        Node head3 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original list: ");
        printList(head3);
        
        head3 = removeNthFromEnd(head3, 2);
        System.out.print("After removing 2nd node from end: ");
        printList(head3);

        // Example 4: Find cycle entry point
        System.out.println("\n=== EXAMPLE 4: Find Cycle Entry Point ===");
        Node head4 = createListWithCycleAtNode(3);
        int cycleEntry = findCycleEntry(head4);
        System.out.println("Cycle entry value: " + cycleEntry);
    }

    /**
     * EXAMPLE 1: Find Middle of Linked List
     * 
     * Problem: Find the middle node of a linked list
     * Input: 1→2→3→4→5
     * Output: 3 (middle node)
     * 
     * APPROACH (Fast & Slow Pointers):
     * - slow pointer moves 1 step
     * - fast pointer moves 2 steps
     * - When fast reaches end, slow is at middle
     * 
     * Visualization:
     * Step 1: slow=1, fast=2
     * Step 2: slow=2, fast=4
     * Step 3: slow=3, fast=null (crossed end)
     * → slow is at middle (3)
     * 
     * Why it works:
     * - fast moves 2x faster than slow
     * - By the time fast reaches end, slow has covered half distance
     * - That's approximately the middle!
     * 
     * Time: O(n), Space: O(1)
     */
    public static Node findMiddle(Node head) {
        Node slow = head;
        Node fast = head;

        System.out.println("Finding middle:");
        int step = 0;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            step++;
            System.out.println("  Step " + step + ": slow=" + slow.data + ", fast=" + (fast != null ? fast.data : "null"));
        }

        return slow;
    }

    /**
     * EXAMPLE 2: Detect if Linked List has a Cycle
     * 
     * Problem: Return true if linked list contains a cycle
     * Input: 1→2→3→4→3 (4 points to 3, creating cycle)
     * Output: true
     * 
     * APPROACH (Fast & Slow Pointers):
     * - Move slow pointer 1 step, fast pointer 2 steps
     * - If they ever point to same node, cycle exists
     * - If fast pointer reaches null, no cycle
     * 
     * Why it works:
     * - In a cycle, eventually fast will "lap" slow
     * - fast moves faster, so it will catch up to slow inside the cycle
     * - No cycle → fast exits first
     * 
     * Visualization with cycle:
     * 1→2→3→4
     *     ↑___↓
     * 
     * Step 1: slow=1, fast=3
     * Step 2: slow=3, fast=3 ✓ MEET (cycle detected)
     * 
     * Time: O(n), Space: O(1)
     */
    public static boolean hasCycle(Node head) {
        if (head == null || head.next == null) {
            return false;
        }

        Node slow = head;
        Node fast = head;

        System.out.println("Detecting cycle:");
        int step = 0;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            step++;

            System.out.println("  Step " + step + ": slow=" + slow.data + ", fast=" + fast.data);

            // If pointers meet, cycle exists
            if (slow == fast) {
                System.out.println("  Pointers met at node: " + slow.data + " → CYCLE DETECTED!");
                return true;
            }
        }

        System.out.println("  Fast reached end → NO CYCLE");
        return false;
    }

    /**
     * EXAMPLE 3: Remove Nth Node from End of List
     * 
     * Problem: Remove the Nth node from the end and return new head
     * Input: 1→2→3→4→5, N=2
     * Output: 1→2→3→5 (removed 4)
     * 
     * APPROACH (Two Pointers with Gap):
     * 1. Create two pointers with N node gap between them
     * 2. Move both pointers together until fast reaches end
     * 3. Slow pointer will be just before node to remove
     * 4. Remove by updating next pointer
     * 
     * Why it works:
     * - fast pointer is N nodes ahead of slow
     * - When fast reaches end, slow is at position (total-N)
     * - So slow.next is the node to remove
     * 
     * Visualization for N=2:
     * Initial: slow=dummy, fast=2
     * 1→2→3→4→5
     * s        f
     * 
     * Move both until fast.next == null:
     * 1→2→3→4→5
     *       s  f
     * 
     * Remove: slow.next = slow.next.next
     * 
     * Time: O(n), Space: O(1)
     */
    public static Node removeNthFromEnd(Node head, int n) {
        System.out.println("Removing " + n + "th node from end:");
        
        // Create dummy node pointing to head (handles removing head edge case)
        Node dummy = new Node(0);
        dummy.next = head;
        Node slow = dummy;
        Node fast = dummy;

        // Create gap of n nodes between slow and fast
        for (int i = 0; i < n; i++) {
            if (fast == null) {
                System.out.println("  N is greater than list length");
                return head;
            }
            fast = fast.next;
            System.out.println("  Step " + i + ": Moving fast to node " + (fast != null ? fast.data : "null"));
        }

        System.out.println("  Gap created: slow at " + slow.data + ", fast at " + fast.data);

        // Move both until fast reaches last node
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            System.out.println("  Moving: slow=" + slow.data + ", fast=" + fast.data);
        }

        System.out.println("  Fast reached end, slow is before removal point");
        System.out.println("  Removing node: " + slow.next.data);
        
        // Remove the node
        slow.next = slow.next.next;

        return dummy.next;
    }

    /**
     * EXAMPLE 4: Find Entry Point of Cycle
     * 
     * Problem: Find the node where cycle begins
     * Input: 1→2→3→4→5
     *            ↑_____|
     * Cycle starts at node 3
     * Output: 3
     * 
     * APPROACH:
     * 1. First detect cycle (slow and fast meet)
     * 2. Reset slow to head
     * 3. Move both pointers one step at a time
     * 4. Where they meet is the cycle entry point
     * 
     * Why it works (Floyd's Cycle Detection Algorithm):
     * - When slow and fast meet, slow has traveled X distance
     * - fast has traveled 2X distance (exactly double)
     * - Distance from head to cycle start = Distance from meeting point to cycle start
     * - So if we move both one step from their meeting point and from head, they meet at entry
     * 
     * Mathematical proof:
     * - Let D = distance from head to cycle start
     * - Let C = cycle length
     * - When they meet: slow has moved D + C*k + m (where m < C)
     *                  fast has moved D + C*j + m
     * - Since fast is 2x: 2(D + C*k + m) = D + C*j + m
     * - Solving: D = C*(j-2k) - m, which means D ≡ -m (mod C)
     * 
     * Time: O(n), Space: O(1)
     */
    public static int findCycleEntry(Node head) {
        System.out.println("Finding cycle entry point:");
        
        Node slow = head;
        Node fast = head;

        // Step 1: Detect cycle
        System.out.println("  Step 1: Detecting cycle...");
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                System.out.println("  Cycle detected at node: " + slow.data);
                break;
            }
        }

        if (fast == null || fast.next == null) {
            System.out.println("  No cycle found");
            return -1;
        }

        // Step 2: Find cycle entry
        System.out.println("  Step 2: Finding cycle entry...");
        slow = head;
        System.out.println("  Reset slow to head: " + slow.data);
        System.out.println("  fast still at meeting point: " + fast.data);

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
            System.out.println("  Moving: slow=" + slow.data + ", fast=" + fast.data);
        }

        System.out.println("  Pointers met at cycle entry: " + slow.data);
        return slow.data;
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
        while (current != null && count < 10) { // Limit iterations to avoid infinite loop for cyclic lists
            System.out.print(current.data + "→");
            current = current.next;
            count++;
        }
        System.out.println("null");
    }
}

