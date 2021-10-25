package data.structures;

import java.util.Stack;

/**  Queue using two Stacks
 * https://www.geeksforgeeks.org/queue-using-stacks/
 * By making deQueue operation costly
 * In this method, in en-queue operation, the new element is entered at the top of stack1.
 * In de-queue operation, if stack2 is empty then all the elements are moved to stack2 and finally top of stack2 is returned.
*/
public class Queue {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    //Function to push an element in queue by using 2 stacks.
    void Push(int x)
    {
        stack1.push(x);
    }


    //Function to pop an element from queue by using 2 stacks.
    int Pop()
    {

        if (stack1.isEmpty() && stack2.isEmpty()) {
            return -1;
        }

        if (stack2.isEmpty()) {

            while (!stack1.isEmpty()) {
                int element = stack1.pop();
                stack2.push(element);
            }

        }

        return stack2.pop();

    }

}
