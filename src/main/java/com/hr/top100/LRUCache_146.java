package com.hr.top100;

import java.util.HashMap;
import java.util.Map;

public class LRUCache_146 {


    private Node head;
    private Node tail;
    private int capacity;
    private Map<Integer, Node> map;

    public LRUCache_146(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, value);
            if (map.size() >= capacity) {
                remove(tail);
            }
        } else {
            node.val = value;
            remove(node);
        }
        addToHead(node);
    }

    private void remove(Node node) {

        if (node == head) head = head.next;
        if (node == tail) tail = tail.pre;
        if (node.pre != null) node.pre.next = node.next;
        if (node.next != null) node.next.pre = node.pre;
        node.pre = null;
        node.next = null;
        map.remove(node.key);
    }

    private void addToHead(Node node) {

        if (head == null) {
            head = tail = node;
        } else {
            // 非头尾节点
            node.next = head;
            head.pre = node;
            head = node;
        }
        map.put(node.key, node);
    }

    static class Node {
        int key;
        int val;
        Node pre;
        Node next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
