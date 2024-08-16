package com.hr.list;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-07
 */
public class DetectCycle_142 {

    public ListNode detectCycle(ListNode head) {

        if (head == null || head.next == null) return head;
        ListNode fast = head, slow = head;

        while (true) {

            if (fast != null && fast.next != null)
                fast = fast.next.next;
            else
                return null;

            slow = slow.next;
            if (fast == slow) break;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow, fast; slow = head; fast = head;

        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        // 相遇
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
