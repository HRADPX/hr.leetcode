package com.hr.list;

import java.util.Arrays;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-13
 */
public class SwapPairs_24 {

    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) return head;
        ListNode p = head, pre = null, newHead = null;

        // 1 2 3 4 5
        while (p != null && p.next != null) {
            ListNode next = p.next;
            ListNode nextStart = next.next;
            next.next = p;
            p.next = nextStart;
            if (pre != null) pre.next = next;
            pre = p;
            p = nextStart;
            if (newHead == null) newHead = next;
        }
        return newHead;
    }

    public ListNode swapPairs2(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p, q;
        p = dummy; q = head;

        // 0, 1, 2, 3, 4
        // 0->2->1->3
        while (q != null && q.next != null) {
            ListNode next = q.next.next;
            p.next = q.next;
            q.next.next = q;
            q.next = next;
            p = q;
            q = next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNodeUtils.buildListNode(Arrays.asList(2, 5, 3, 4, 6, 2, 2));
        SwapPairs_24 instance = ReflectUtils.getInstance(SwapPairs_24.class);
        System.out.println(ListNodeUtils.traversalList(instance.swapPairs2(head)));
    }
}
