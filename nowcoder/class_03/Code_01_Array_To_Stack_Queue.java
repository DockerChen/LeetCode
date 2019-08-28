package class_03;

public class Code_01_Array_To_Stack_Queue {
    //栈
    public static class ArrayStack {
        private Integer[] arr;
        private Integer size;
//构造函数，初始化栈
        public ArrayStack(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("The init size is less than 0");
            }
            arr = new Integer[initSize];
            size = 0;
        }
//返回栈顶元素
        public Integer peek() {
            if (size == 0) {
                return null;
            }
            return arr[size - 1];
        }
//压栈
        public void push(int obj) {
            if (size == arr.length) {
                throw new ArrayIndexOutOfBoundsException("The queue is full");
            }
            arr[size++] = obj;
        }
//删除栈顶元素
        public Integer pop() {
            if (size == 0) {
                throw new ArrayIndexOutOfBoundsException("The queue is empty");
            }
            return arr[--size];
        }
    }

    //队列
    public static class ArrayQueue {

        private Integer[] arr;
        private Integer size;
        private Integer first;
        private Integer last;

//初始化队列
        public ArrayQueue(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("The init size is less than 0");
            }
            arr = new Integer[initSize];
            size = 0;
            first = 0;
            last = 0;
        }
//返回队头元素
        public Integer peek() {
            if (size == 0) {
                return null;
            }
            return arr[first];
        }
//将元素插入到队尾
        public void push(int obj) {
            if (size == arr.length) {
                throw new ArrayIndexOutOfBoundsException("The queue is full");
            }
            size++;
            arr[last] = obj;
            last = last == arr.length - 1 ? 0 : last + 1;
        }
//删除队头元素
        public Integer poll() {
            if (size == 0) {
                throw new ArrayIndexOutOfBoundsException("The queue is empty");
            }
            size--;
            int tmp = first;
            first = first == arr.length - 1 ? 0 : first + 1;
            return arr[tmp];
        }
    }

    public static void main(String[] args) {

    }

}
