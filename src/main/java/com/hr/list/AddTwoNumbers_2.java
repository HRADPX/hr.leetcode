package com.hr.list;

import com.hr.utils.ReflectUtils;

import java.util.Arrays;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-08
 */
public class AddTwoNumbers_2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l2 == null) return l1;
        if (l1 == null) return l2;
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        int bit = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            p.next = new ListNode((v1 + v2 + bit) % 10);
            bit = (v1 + v2 + bit) / 10;
            p = p.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (bit > 0) p.next = new ListNode(1);
        return dummy.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        int bit = 0;

        while (l1 != null || l2 != null) {

            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int sum = v1 + v2 + bit;
            bit = sum / 10;
            p.next = new ListNode(sum % 10);
            p = p.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (bit > 0) {
            p.next = new ListNode(bit);
        }
        return dummy.next;
    }

//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        if (l1 == null) return l2;
//        if (l2 == null) return l1;
//        ListNode dummy = new ListNode();
//        ListNode p = dummy;
//
//        int bit = 0;
//        while (l1 != null && l2 != null) {
//            int sum = l1.val + l2.val + bit;
//            bit = sum / 10;
//            p.next = new ListNode(sum % 10);
//            p = p.next;
//            l1 = l1.next;
//            l2 = l2.next;
//        }
//        if (l1 == null) {
//            while (l2 != null) {
//                int sum = l2.val + bit;
//                p.next = new ListNode(sum % 10);
//                bit = sum / 10;
//                l2 = l2.next;
//                p= p.next;
//            }
//        }
//        if (l2 == null) {
//            while (l1 != null) {
//                int sum = l1.val + bit;
//                p.next = new ListNode(sum % 10);
//                bit = sum / 10;
//                l1 = l1.next;
//                p= p.next;
//            }
//        }
//        if (bit > 0) p.next = new ListNode(1);
//        return dummy.next;
//    }

    public static void main(String[] args) {

        ListNode l1 = ListNodeUtils.buildListNode(Arrays.asList(2,4,3));
        ListNode l2 = ListNodeUtils.buildListNode(Arrays.asList(5,6,6));
        AddTwoNumbers_2 instance = ReflectUtils.getInstance(AddTwoNumbers_2.class);
        System.out.println(ListNodeUtils.traversalList(instance.addTwoNumbers(l1, l2)));

    }
}
