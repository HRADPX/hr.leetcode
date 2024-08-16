package com.hr.list;

import com.hr.utils.ReflectUtils;

/**
 * 链表奇数位升序，偶数位降序，将这个链表重新排序
 */
public class Merge2NewListNode {

    // 1->9->2->8->3
    //
    public ListNode reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = new ListNode();
        ListNode even = new ListNode();
        ListNode p = odd, q = even;

        while (head != null) {
            p.next = head;
            q.next = head.next;
            p = p.next;
            q = q.next;
            head = head.next == null ? null : head.next.next;
            p.next = null;
            if (q != null) {
                q.next = null;
            }
        }
        return this.mergeList(odd.next, ListNodeUtils.reverseList(even.next));
    }

    private ListNode mergeList(ListNode h1, ListNode h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        ListNode dummy = new ListNode();
        ListNode node = dummy;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                node.next = new ListNode(h1.val);
                h1 = h1.next;
            } else {
                node.next = new ListNode(h2.val);
                h2 = h2.next;
            }
            node = node.next;
        }
        if (h1 != null) {
            node.next = h1;
        }
        if (h2 != null) {
            node.next = h2;
        }
        return dummy.next;
    }

    public static void main(String[] args){

        ListNode head = ListNodeUtils.buildListNode(1, 0);
        System.out.println(ListNodeUtils.traversalList(ReflectUtils.getInstance(Merge2NewListNode.class).reorderList(head)));
    }

}
