package com.hr.list;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-07
 *
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 * 解题思路：双指针
 */
public class MiddleNode_876 {

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
