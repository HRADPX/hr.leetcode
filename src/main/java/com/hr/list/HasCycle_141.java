package com.hr.list;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-07
 */
public class HasCycle_141 {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode fast = head.next, slow = head;

        while (fast != slow) {
            if (fast.next == null) return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast, slow;
        slow = fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
