package com.hr.list;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-31
 */
public class RotateRight_61 {

    // 成环
    public ListNode rotateRight_circle(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;

        ListNode node = head;
        int length = 1;
        while(node.next != null) {
            length++;
            node = node.next;
        }
        node.next = head; // cycle

        node = head;
        k %= length;
        while(k >= 0) {
            node = node.next;
            --k;
        }
        ListNode slow = head;
        while(node != head){

            node = node.next;
            slow = slow.next;
        }
        node = slow.next;
        slow.next = null;
        return node;

    }

    public ListNode rotateRight2(ListNode head, int k) {

        if (k <= 0 || head == null || head.next == null) {
            return head;
        }
        int len = 0;
        ListNode slow, fast;
        slow = fast = head;

        // 1 2 3 4 5
        while (fast != null) {
            fast = fast.next;
            len++;
        }
        k %= len;
        if (k == 0) {
            return head;
        }
        fast = head;
        int i = 0;
        while (i < k) {
            fast = fast.next;
            i++;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }

    // 常规思路
    public ListNode rotateRight(ListNode head, int k) {

        if (k == 0 || head == null || head.next == null) return head;
        ListNode fast, slow; slow = fast = head;

        int len = 0;
        while (fast != null) {
            fast = fast.next;
            len++;
        }
        fast = head;
        k %= len;
        if (k == 0) return head;

        int i = 0;
        while (i < k) {
            fast = fast.next;
            ++i;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }

    public static void main(String[] args) {

        ListNode head = ListNodeUtils.buildListNode(1, 2, 3, 4, 5);
        RotateRight_61 instance = ReflectUtils.getInstance(RotateRight_61.class);
        System.out.println(ListNodeUtils.traversalList(instance.rotateRight2(head, 3)));
    }
}
