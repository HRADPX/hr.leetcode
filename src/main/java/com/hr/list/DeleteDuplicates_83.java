package com.hr.list;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-14
 */
public class DeleteDuplicates_83 {

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) return head;
        ListNode p = head, pre = null;

        while (p != null) {
            ListNode next = p.next;
            if (pre != null && pre.val == p.val) {
                pre.next = next;
                p.next = null;
            } else
                pre = p;
            p = next;
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head.next, pre = head;

        // 1->2->3->3
        while (p != null) {
            ListNode next = p.next;
            if (p.val == pre.val) {
                pre.next = next;
                p.next = null; // gc
            } else {
                pre = p;
            }
            p = next;
        }
        return head;
    }

    public static void main(String[] args) {

        ListNode head = ListNodeUtils.buildListNode(1, 1, 1);
        DeleteDuplicates_83 instance = ReflectUtils.getInstance(DeleteDuplicates_83.class);
        System.out.println(ListNodeUtils.traversalList(instance.deleteDuplicates(head)));
    }
}
