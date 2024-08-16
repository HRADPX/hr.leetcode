package com.hr.list;

import java.util.Arrays;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-13
 */
public class ReverseBetween_92 {

    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy;
        ListNode front, after;
        int i = 0;
        while (i < left - 1) {
            p = p.next;
            i++;
        }
        front = p;
        while (i < right) {
            p = p.next;
            i++;
        }
        ListNode end = p;
        ListNode pre = end.next;
        after = end.next;
        p = front.next;

        // 0 1, [2, 3, 4], 5, 6
        //   pre       end
        while (p != after) {
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        front.next = end;
        return dummy.next;
    }

    public ListNode reverseBetween3(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy;
        ListNode front;
        int i = 0;
        while (i < left - 1) {
            p = p.next;
            i++;
        }
        front = p;
        while (i < right) {
            p = p.next;
            i++;
        }
        front.next = reverse(front.next, p.next);;
        return dummy.next;
    }

    // 1 2 3 4
    private ListNode reverse(ListNode start, ListNode end) {
        if (start == end) {
            return start;
        }
        ListNode pre = null;
        ListNode p = start;

        while (p != end) {
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        start.next = end;
        return pre;
    }

    public ListNode reverseBetweenV1(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) return head;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy;
        ListNode pre;

        int i = 0;
        while (i < left - 1) {
            p = p.next;
            ++i;
        }
        pre = p;
        while (i < right) {
            p = p.next;
            ++i;
        }
        ListNode end = p;
        ListNode pp = p.next;
        ListNode rr = p.next;
        p = pre.next;

        while (p != rr) {
            ListNode next = p.next;
            p.next = pp;
            pp = p;
            p = next;

        }
        pre.next = end;
        return dummy.next;
    }

    /**
     * 如果我们把 head 的索引视为 1，那么我们是想从第 m 个元素开始反转对吧；
     * 如果把 head.next 的索引视为 1 呢？那么相对于 head.next，反转的区间应该是从第 m - 1 个元素开始的；
     * 那么对于 head.next.next 呢……
     */
    ListNode reverseBetween(ListNode head, int m, int n) {
        // base case
        if (m == 1)
            return reverseN(head, n);
        // 前进到反转的起点触发 base case
        ListNode last = reverseBetween(head.next, m - 1, n - 1);
        head.next = last;
        return head;
    }

    ListNode successor = null; // 后驱节点

    // 反转以 head 为起点的 n 个节点，返回新的头结点
    private ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = this.reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }


    public static void main(String[] args) {

        ListNode head = ListNodeUtils.buildListNode(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListNode res = ReflectUtils.getInstance(ReverseBetween_92.class).reverseBetween3(head, 1, 4);
        System.out.println(ListNodeUtils.traversalList(res));

    }
}
