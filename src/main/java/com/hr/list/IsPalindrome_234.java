package com.hr.list;

import java.util.Arrays;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-08
 *
 * 用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题
 */
public class IsPalindrome_234 {

    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) return true;
        ListNode fast, slow, pre; fast = slow = head; pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        if (pre != null) pre.next = null;
        slow = this.convert(slow);
        fast = head;

        while (slow != null && fast != null) {
            if (slow.val != fast.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    // 1 2 3 2 1
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow, fast, pre;
        slow = fast = head; pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        if (pre != null) pre.next = null; // 断开链表, 后面的可能会多一个
        slow = convert(slow);
        fast = head;
        while (slow != null && fast != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    private ListNode convert(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre, p; p = head; pre = null;
        while (p != null) {
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        return pre;
    }

    public static void main(String[] args) {

        ListNode head = ListNodeUtils.buildListNode(Arrays.asList(3, 3));
        IsPalindrome_234 instance = ReflectUtils.getInstance(IsPalindrome_234.class);
        System.out.println(instance.isPalindrome(head));
    }
}
