/**
 * // Length of zero is empty, therefore the list starts at 1 (one).
 * 
 * @author derrick
 * @date July 2014
 */
public class LList implements ListInterface {
	
	private Node firstNode;			// reference to the first node
	private int length;				// number of entries in the list
	
	public LList()
	{
		clear();
	} // end default constructor
	
	public final void clear()
	{
		firstNode = null;
		length = 0;
	} // end clear
	
	/**
	 * // Length of zero is empty, therefore the list starts at 1 (one).
	 */
	public boolean isEmpty()
	{
		return length == 0;
	}
	
	/**
	 * Method adds to the end of the List.
	 * Method requires traversing the chain to locate current last node.
	 * Then you give the current last node the address of the new node.
	 * Now they are linked and the new node is now the last node.
	 */
	public boolean add(Object newEntry)
	{
		Node newNode = new Node(newEntry);
		
		if (isEmpty())
		{	
			firstNode = newNode;
		}
		else
		{
			/**
			 * gets last node
			 * if there are 2 nodes, length = 2 because the list starts at 1 (one)
			 * then sets node 2's next to new node
			 * so new node becomes the last node in the chain
			 */
			Node lastNode = getNodeAt(length);
			lastNode.next = newNode;	// make current last node reference new node
		} // end if/else
		
		length++;	// increment chain's length by one.
		return true;
	} // end add
	
	/**
	 * newNode references the new node
	 * nodeBefore references the node that will be before the new node
	 * Set nodeAfter to nodeBefore's link
	 * Set newNode's link to nodeAfter
	 * Set nodeBefore's link to newNode
	 */
	public boolean add(int newPosition, Object newEntry)
	{
		boolean isSuccessful = true;
		
		if ((newPosition >=1) && (newPosition <= length +1))
		{
			Node newNode = new Node(newEntry);
			
			if (isEmpty() || (newPosition==1)) // adding to beginning of the list
			{
				newNode.next = firstNode;
				firstNode = newNode;
			}
			else	// newPosition > 1, list is not empty
			{
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.next;
				newNode.next = nodeAfter;
				nodeBefore.next = newNode;
			} // end if/else
			
			length++;
		} // end if
		else
			isSuccessful = false;
		return isSuccessful;

	} // end add
	
	public Object remove(int givenPosition)
	{
		Object result = null;	// return value
		
		if (!isEmpty() && (givenPosition >= 1) && (givenPosition <= length))
		{
			if (givenPosition == 1)		// case 1: remove 1st entry
			{
				result = firstNode.data;	// save entry to be removed
				firstNode = firstNode.next;
			}
			else
			{
				Node nodeBefore = getNodeAt(givenPosition - 1);
				Node nodeToRemove = nodeBefore.next;
				Node nodeAfter = nodeToRemove.next;
				nodeBefore.next = nodeAfter;	// disconnect the node to be removed
				result = nodeToRemove.data;		// save entry to be removed
			}
			length--;
		} // end if
		return result;	// return removed entry, or null if operation fails
	} // end remove
	
	public boolean replace(int givenPosition, Object newEntry)
	{
		boolean isSuccessful = true;
		
		if (!isEmpty() && (givenPosition >=1) && (givenPosition <= length))
		{
			Node desiredNode = getNodeAt(givenPosition);
			desiredNode.data = newEntry;
		}
		else
			isSuccessful = false;
		return isSuccessful;
	} // end replace
	
	public Object getEntry(int givenPosition)
	{
		Object result = null;	// result to return
		
		if (!isEmpty() && (givenPosition >= 1) && (givenPosition <= length))
			result = getNodeAt(givenPosition).data;
		
		return result;
	} // end getEntry
	
	public boolean contains(Object anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} // end while
		
		return found;
	} // end contains
	
	/**
	 * This should always return false.  The only time a list whose implementation
	 * is linked could appear full is when they system can not provide enough 
	 * memory to the add method for a new node.  In that case, an OutOfMemoryError
	 * occurs, which is fatal.  A client would not have the opportunity to call
	 * isFull.
	 */
	public boolean isFull() 
	{
		return false;
	} // end isFull
	
	public int getLength()
	{
		return length;
	} // end getLength
	
	public void display()
	{
		Node currentNode = firstNode;
		
		while (currentNode != null)
		{
			System.out.println(currentNode.data);
		}
	} // end display

	
	/**
	 * ----------- PRIVATE METHODS --------------------
	 */
	
	private Node getNodeAt(int givenPosition)
	{
		Node currentNode = firstNode;
		
		//traverse the list to locate the desired node
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.next;
		
		return currentNode;
	} // end getNodeAt

	private class Node		// private inner class 
	{
		private Object data;		// data portion
		private Node next;			// link to next node
		
		private Node(Object dataPortion)
		{
			data = dataPortion;
			next = null;
		} // end constructor
		
		private Node(Object dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;
		} // end constructor
	} // end Node

}
