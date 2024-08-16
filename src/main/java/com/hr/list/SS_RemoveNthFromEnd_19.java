package com.hr.list;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-07
 *
 * 慢指针为什么从占位节点开始？
 * 因为题目要求是删除倒数第 K 个节点而不是找到倒数第 K 个节点，所以慢指针要停留在倒数 K + 1 个节点的位置（即前一个节点的位置）
 * 才能断开指向倒数第 K 个节点的指针。
 * 以 K 为 2 为例，链表结构为（1->2->3->4->5），快慢指针（f,s）刚开始都在头节点，快指针先走两步到达节点 3，然后快慢指针同时向前移动，直到快指针到达链尾 null.
 * 1 2 3 4 5   ->   1 2 3 4 5 null
 * s   f       ->         s    f
 * 此时慢指针(s) 的位置正好是倒数第 2 个节点的位置。
 * 但是本题要求是删除倒数第 2 个节点，所以需要断开 3 指向 4 的指针，实际上需要操作的是 3 这个节点（即倒数第 3 个节点），所以慢指针需要停留在 3 这个位置。
 * 引入占位节点后，慢指针从占位节点开始，就能满足条件。如下图所示，此时只要将 3 的 next 指针指向 5 即可，即 s.next = s.next.next。
 *
 * 0 1 2 3 4 5   ->   0 1 2 3 4 5 null
 * s     f       ->         s      f
 *
 * 为什么不引入额外变量记录慢指针的前一个节点？
 * 因为慢指针的前一个节点最终结果可能 null，如 K = 1，链表长度也是 1，即需要删除整个链表，此时前一个节点是 null 无法执行操作。
 */
public class SS_RemoveNthFromEnd_19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (n <= 0 || head == null) return head;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = head, slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {

        if (n <= 0 || head == null) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = head;
        // 0 [1 2 3 4 5]
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        ListNode slow = dummy;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args){

        ListNode head = ListNodeUtils.buildListNode(1, 2, 3, 4, 5);
        SS_RemoveNthFromEnd_19 instance = ReflectUtils.getInstance(SS_RemoveNthFromEnd_19.class);
        System.out.println(ListNodeUtils.traversalList(instance.removeNthFromEnd2(head, 5)));
    }

}
