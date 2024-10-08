package BusManage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class MyList {

    //data member
    Node head;
    Node tail;

    //contructor
    public MyList() {
        head = null;
        tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = null;
        tail = null;
    }

    void addLast(Bus x) {
        if (isEmpty()) {
            Node p = new Node(x);//(1) -> p.next = null;
            head = p;
            tail = p;
        } else {
            Node p = new Node(x);//(1) -> p.next = null;
            tail.next = p;
            tail = p;
        }

    }

    void visit(Node p) {
        System.out.print(p.info + " ");
    }

    void traverse() {
        Node p = head;
        while (p != null) {
            visit(p);
            p = p.next;
        }
        System.out.println();
    }

    void addManyLast(Bus[] a) {
        for (int i = 0; i < a.length; i++) {
            addLast(a[i]);
        }
    }

    void addFirst(Bus x) {
        Node p = new Node(x);
        if (isEmpty()) {
            head = p;
            tail = p;
        } else {
            p.next = head;
            head = p;
        }
    }

    void removeFirst() {
        if (head == tail) {

            clear();
        } else {
            Node p = head;
            head = head.next;
            p.next = null;

        }

    }

    void removeLast() {
        if (head == tail) {

            clear();
        } else {
            Node p = head;
            while (p.next != tail) {
                p = p.next;
            }
            p.next = null;
            tail = p;

        }
    }

    Node getFirst() {
        return head;
    }

    Node getLast() {
        return tail;

    }

    Node searchByBcode(String code) {
        Node current = head;
        while (current != null) {
            if (current.info.getBcode().equals(code)) {
                return current;
            }
            current = current.next;
        }
        return null;

    }
    
    
    


//    Node searchByMark(int x) {
//        Node p = head;
//        while (p != null) {
//            if (p.info.mark == x) {
//                return p;
//            }
//            p = p.next;
//
//        }
//        return null;
//
//    }
//    
//    Node searchByName(Student x) {
//        Node p = head;
//        int count=0;
//        while (p != null) {
//            if (p.info.name.contains(x.name)) {
//                count++;
//                if(count==1) return p;
//                
//            }
//            p = p.next;
//
//        }
//        return null;
//
//    }
//    
//    
    Node getNext(Node p) {
        if (p == null) {
            return null;
        }
        return p.next;

    }

    Node getPrev(Node p) {
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        return q;

    }

    int size() {
        int count = 0;
        Node p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;

    }

    Node get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        int count = 0;
        Node p = head;
        while (p != null) {
            count++;
            if (count == index + 1) {
                return p;
            }
            p = p.next;

        }

        return p;

    }

    void remove(Node p) {
        if (p == null) {
            System.out.println("Cannot remove a null node.");
            return; // Không thể xóa nút null
        }

        if (p == head) {
            removeFirst();
            return; // Trả về sau khi xóa nút đầu
        }

        if (p == tail) {
            removeLast();
            return; // Trả về sau khi xóa nút cuối
        }

        Node q = getPrev(p);
        if (q != null) {
            q.next = p.next; // Bỏ liên kết nút p ra khỏi danh sách
            p.next = null; // Đặt lại liên kết của p
        } else {
            System.out.println("Node is not in the list.");
        }
    }

    void remove(int index) {
        Node p = get(index);
        if (p == null) {
            System.out.println("Invalid index");
        } else {
            remove(p);
        }
    }

    void swap(Node p, Node q) {
        Bus temp;
        temp = p.info;
        p.info = q.info;
        q.info = temp;
    }

    void sortByBcode() {
        int n = size(); // Lấy kích thước của danh sách
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                Node pi = get(i); // Lấy nút tại vị trí i
                Node pj = get(j); // Lấy nút tại vị trí j
                // So sánh bcode và hoán đổi nếu cần thiết
                if (pi.info.getBcode().compareTo(pj.info.getBcode()) > 0) {
                    // Hoán đổi thông tin giữa hai nút
                    Bus temp = pi.info; // Giả sử Bus là lớp chứa thông tin xe buýt
                    pi.info = pj.info;
                    pj.info = temp;
                }
            }
        }
    }

    void insert(int index, Bus x) {
        if (index < 0 || index > size()) {

            return;
        }

        if (index == 0) {
            addFirst(x);

        }

        if (index == size()) {
            addLast(x);

        }

        Node newNode = new Node(x);
        Node prev = get(index - 1);
        if (prev != null) {
            newNode.next = prev.next;
            prev.next = newNode;
        }
    }




}

//    void insertAfter(Node p, Student x) {
//        if (p == tail) {
//            addLast(x);
//
//        } else {
//
//            Node newNode = new Node(x);
//            newNode.next = p.next;
//            p.next = newNode;
//
//        }
//
//    }
//
//    void insertBefore(Node p, Student x) {
//        if (p == null) {
//
//            return;
//        }
//
//        if (p == head) {
//            addFirst(x);
//            return;
//        }
//
//        Node newNode = new Node(x);
//        Node prev = getPrev(p);
//        if (prev != null) {
//            newNode.next = p;
//            prev.next = newNode;
//        }
//    }
//
//    void insert(int index, Student x) {
//        if (index < 0 || index > size()) {
//
//            return;
//        }
//
//        if (index == 0) {
//            addFirst(x);
//
//        }
//
//        if (index == size()) {
//            addLast(x);
//
//        }
//
//        Node newNode = new Node(x);
//        Node prev = get(index - 1);
//        if (prev != null) {
//            newNode.next = prev.next;
//            prev.next = newNode;
//        }
//    }
//
//    void remove(Node p) {
//        if (p == head) {
//            removeFirst();
//        }
//
//        if (p == tail) {
//            removeLast();
//        }
//        Node q = getPrev(p);
//        q.next = p.next;
//        p.next = null;
//    }
//
//    void remove(int index) {
//        Node p = get(index);
//        if (p == null) {
//            System.out.println("Invalid index");
//        } else {
//            remove(p);
//        }
//    }
//
//    void removeAfter(Node p) {
//        remove(getNext(p));
//    }
//
//    void removeBefore(Node p) {
//        remove(getPrev(p));
//    }
//
//    void set(Node p, Student x) {
//        p.info = x;
//    }
//
//    Node max() {
//        Node p = get(0);
//        for (int i = 1; i < size(); i++) {
//            if (p.info.mark < get(i).info.mark) {
//                p = get(i);
//            }
//        }
//        return p;
//    }
//
//    Node secondMax() {
//        Node p = get(0);
//        Node seMax = null;
//        for (int i = 1; i < size(); i++) {
//            if (p.info.mark < get(i).info.mark) {
//                seMax = p;
//                p = get(i);
//            } else if (get(i).info.mark > seMax.info.mark && get(i).info.mark != p.info.mark) {
//                seMax = get(i);
//            }
//        }
//        return seMax;
//    }
//
//    void swap(Node p, Node q) {
//        Student temp;
//        temp = p.info;
//        p.info = q.info;
//        q.info = temp;
//    }
//
//    void sort() {
//        int n = size();
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = i + 1; j < n; j++) {
//                Node pi = get(i), pj = get(j);
//                if (pi.info.mark > pj.info.mark) {
//                    swap(pj, pj);
//                }
//            }
//        }
//    }
//    void sortById() {
//        int n = size();
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = i + 1; j < n; j++) {
//                Node pi = get(i), pj = get(j);
//                if (pi.info.sid.compareTo(pj.info.sid)>0) {
//                    swap(pj, pj);
//                }
//            }
//        }
//    }
//    
//    void sortByName() {
//        int n = size();
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = i + 1; j < n; j++) {
//                Node pi = get(i), pj = get(j);
//                if (pi.info.name.compareTo(pj.info.name)>0) {
//                    swap(pj, pj);
//                }
//            }
//        }
//    }
//
//    void sort(Node p, Node q) {
//        Node pi = p;
//        while (pi != q.next) {
//            Node pj = pi.next;
//            while (pj != q.next) {
//                if (pi.info.mark > pj.info.mark) {
//                    swap(pi, pj);
//                }
//                pj = pj.next;
//            }
//            pi = pi.next;
//        }
//
//    }
//
//    void reverse()
//    {
//        Node p = head;
//        Node q = null;
//        Node r = null;
//        tail = head;
//        while (p != null)
//        {
//            r = q;
//            q = p;
//            p = p.next;
//            q.next = r;
//        }
//        head = q;
//    }
//
//}
