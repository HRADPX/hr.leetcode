package com.hr.list;

import java.util.Arrays;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-14
 */
public class ReverseKGroup_25 {

    // 迭代实现
    public ListNode reverseKGroup2(ListNode head, int k) {

        if(head == null || head.next == null || k <= 1) return head;
        ListNode dummy = new ListNode(0);
        ListNode p = dummy, cur;
        dummy.next = head;

        while (true) {

            int i = 1;
            cur = p.next;
            ListNode curHead = cur;
            while (i < k && cur != null) {
                cur = cur.next;
                i++;
            }
            if (cur == null) break;
            ListNode nextHead = cur.next;
            cur.next = null;
            p.next = revese(curHead);
            p = curHead;
            p.next = nextHead;
        }
        return dummy.next;
    }

    private ListNode revese(ListNode head){

        if(head == null || head.next == null) return head;
        ListNode cur = head, pre = null;
        while(cur != null){

            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 递归实现
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) return head;

        ListNode p = head; int i = 0;
        // 1->2->3
        while (p != null && i < k - 1) {
            p = p.next;
            i++;
        }
        if (p == null) return head;
        ListNode nextHead = p.next;
        // 3->2->1
        ListNode newHead = reverse(head, nextHead);
        head.next = reverseKGroup(nextHead, k);
        return newHead;
    }

    private ListNode reverse(ListNode start, ListNode end) {
        ListNode p = start, pre = null;
        while (p != end) {
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = ListNodeUtils.buildListNode(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        ReverseKGroup_25 instance = ReflectUtils.getInstance(ReverseKGroup_25.class);
        System.out.println(ListNodeUtils.traversalList(instance.reverseKGroup(head, 3)));
    }
}
