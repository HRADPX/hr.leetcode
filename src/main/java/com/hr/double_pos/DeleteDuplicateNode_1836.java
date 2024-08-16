package com.hr.double_pos;

import com.hr.list.ListNode;
import com.hr.list.ListNodeUtils;
import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-12
 *
 * 从未排序的链表中移除重复的数据，链表的值不超过 105
 */
public class DeleteDuplicateNode_1836 {

    public ListNode deleteDuplicateNode(ListNode head) {
        if (head == null || head.next == null) return head;
        int[] count = new int[106];

        ListNode p = head;
        while (p != null) {
            count[p.val]++;
            p = p.next;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy; p = head;

        while (p != null) {
            if (count[p.val] == 1) {
                pre = p;
                p = p.next;
                continue;
            }
            pre.next = p.next;
            p = p.next;
        }
        return dummy.next;
    }
}
