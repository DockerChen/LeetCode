import java.util.ArrayDeque;

public class MaxQueue_59_2 {
    public class MaxQueue {
        class InternalData {
            int number;
            int index;

            public InternalData(int number, int index) {
                this.number = number;
                this.index = index;
            }
        }

        ArrayDeque<InternalData> data;
        ArrayDeque<InternalData> maxData;
        int curIndex;

        public MaxQueue() {
            curIndex = 0;

        }

        public int max() throws Exception {
            if (maxData.isEmpty()) {
                throw new Exception("maxData is empty");
            }
            return maxData.pollFirst().number;

        }

        public void push_back(int number) {
            while (!maxData.isEmpty() && maxData.peekLast().number <= number) {
                maxData.pollLast();
            }
            InternalData internalData = new InternalData(number, curIndex);
            data.addLast(internalData);
            maxData.addLast(internalData);
            curIndex++;

        }

        public void pop_front() throws Exception {
            if (maxData.isEmpty()) {
                throw new Exception("maxData is empty");
            }
            if (maxData.peekFirst() == data.peekFirst()) {
                maxData.pollFirst();
            }
            data.pollFirst();

        }


    }


}
