package com.hr.list;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-07
 * 考虑一题多解
 * 1.遍历即可
 * 2.让两个链表到末尾的长度相等
 * 3.如果两个链表有相交，让链表首尾相连一定形成一个环，判断环的入口即可。
 */
public class GetIntersectionNode_160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode p = headA, q = headB;
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }

    /**
     * 计算两个链表的长度，根据长度让两个指针到达尾部的距离相同
     */
    public ListNode getIntersectionNodeV1(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;
        // 计算两条链表的长度
        for (ListNode p1 = headA; p1 != null; p1 = p1.next) {
            lenA++;
        }
        for (ListNode p2 = headB; p2 != null; p2 = p2.next) {
            lenB++;
        }
        // 让 p1 和 p2 到达尾部的距离相同
        ListNode p1 = headA, p2 = headB;
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                p1 = p1.next;
            }
        } else {
            for (int i = 0; i < lenB - lenA; i++) {
                p2 = p2.next;
            }
        }
        // 看两个指针是否会相同，p1 == p2 时有两种情况：
        // 1、要么是两条链表不相交，他俩同时走到尾部空指针
        // 2、要么是两条链表相交，他俩走到两条链表的相交点
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    /**
     * 让链表的头尾相连，如果有相交，肯定会形成一个环，就可以转化为找到环的入口，题目要求不能改变链表结构，最后返回的时候需要将链表首尾断开
     */
    public ListNode getIntersectionNodeV2(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        while (p1.next != null) p1 = p1.next;
        p1.next = headB;
        ListNode fast = headA, slow = headA;

        while (true) {
            if (fast != null && fast.next != null)
                fast = fast.next.next;
            else {
                p1.next = null;  // 恢复链表结构
                return null;
            }

            slow = slow.next;
            if (fast == slow) break;
        }
        slow = headA;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        p1.next = null;     // 恢复链表结构
        return slow;
    }
}
