package linkedlist;


import java.util.Arrays;

import org.junit.Test;

import student.TestCase;

/**
 * 
 * Tests the equals and toArray methods of a singly linked list.
 * 
 * @author Margaret Ellis (maellis1)
 * 
 * @author Jeff Robertson (thejar)
 * 
 * @version 03/19/2017
 *
 */
public class SLLEqualsToArrayTest extends TestCase {

    private SinglyLinkedList<String> emptyListA;
    private SinglyLinkedList<String> emptyListB;
    private SinglyLinkedList<String> smallListA;
    private SinglyLinkedList<String> smallListB;
    private SinglyLinkedList<String> bigListA;
    private SinglyLinkedList<String> bigListB;
    private String nullObject;


    /**
     * Initializes 2 empty lists, 2 lists with a small number of items, and 2
     * lists with a large number of items
     */
    public void setUp() {
        emptyListA = new SinglyLinkedList<String>();
        emptyListB = new SinglyLinkedList<String>();

        smallListA = new SinglyLinkedList<String>();
        smallListB = new SinglyLinkedList<String>();

        smallListA.add("soccer");
        smallListA.add("swimming");
        smallListA.add("gymnastics");

        smallListB.add("soccer");
        smallListB.add("swimming");
        smallListB.add("gymnastics");

        bigListA = new SinglyLinkedList<String>();

        for (int i = 0; i < 100; i++) {
            bigListA.add("sport" + i);
        }

        bigListB = new SinglyLinkedList<String>();
        for (int i = 0; i < 100; i++) {
            bigListB.add("sport" + i);
        }
        
        // to be explicit
        nullObject = null;
    }


    /**
     * Tests the equals method on an empty list
     */
    public void testEqualsEmptyList() {
        assertEquals(emptyListA, emptyListA);
        assertEquals(emptyListA, emptyListB);
        assertFalse(emptyListA.equals(nullObject));
        assertFalse(emptyListA.equals("soccer"));
        assertFalse(emptyListA.equals(smallListA));
        assertFalse(smallListA.equals(emptyListA));
        emptyListB.add("jump roping");
        assertFalse(emptyListA.equals(emptyListB));
        smallListA.clear();
        assertEquals(emptyListA, smallListA);
    }


    /**
     * Tests the equals method on a list with a small number of items in it
     */
    public void testEqualsSmallList() {
        assertEquals(smallListA, smallListA);
        assertEquals(smallListA, smallListB);
        assertFalse(smallListA.equals(nullObject));
        assertFalse(smallListA.equals("soccer"));
        assertFalse(smallListA.equals(bigListA));
        assertFalse(smallListA.equals(emptyListA));
        smallListB.add("jump roping");
        assertFalse(smallListA.equals(smallListB));

        // Make smallListA and smallListB differ in
        // content, but have the same size
        smallListA.add("rope jumping");
        assertFalse(smallListA.equals(smallListB));

        // Replace the last element in smallListA
        // to make smallListA and smallListB equal again
        smallListA.remove("rope jumping");
        smallListA.add("jump roping");
        assertEquals(smallListA, smallListB);
    }


    /**
     * Tests the equals method on a list with a large number of items in it
     */
    public void testEqualsBigList() {
        assertEquals(bigListA, bigListA);
        assertEquals(bigListA, bigListB);
        assertFalse(bigListA.equals(nullObject));
        assertFalse(bigListA.equals("soccer"));
        assertFalse(bigListA.equals(smallListA));
        assertFalse(bigListA.equals(emptyListA));
        bigListB.add("jump roping");
        assertFalse(bigListA.equals(bigListB));

        // Same content, same size, but reversed
        bigListB.clear();
        assertFalse(bigListA.equals(bigListB));
        for (int i = 100; i > 0; i--) {
            bigListB.add("sport" + i);
        }
        assertFalse(bigListA.equals(bigListB));

        // one a subset of the other but with dups
        bigListB.clear();
        assertFalse(bigListA.equals(bigListB));
        for (int i = 0; i < 50; i++) {
            bigListB.add("sport" + i);
        }
        for (int i = 0; i < 50; i++) {
            bigListB.add("sport" + i);
        }
        assertFalse(bigListA.equals(bigListB));

        // make them equal again
        bigListB.clear();
        assertFalse(bigListA.equals(bigListB));
        for (int i = 0; i < 100; i++) {
            bigListB.add("sport" + i);
        }
        assertEquals(bigListA, bigListB);

    }


    /**
     * Tests the toArray method on an empty list
     */
    public void testToArrayEmpty() {

        Object[] emptyArray = {};
        assertTrue(Arrays.equals(emptyListA.toArray(), emptyArray));
        assertTrue(Arrays.equals(emptyListA.toArray(), emptyListB.toArray()));
        assertFalse(Arrays.equals(emptyListA.toArray(), smallListB.toArray()));
        Object[] oneItemArray = { "one thing" };
        emptyListA.add("one thing");
        assertTrue(Arrays.equals(emptyListA.toArray(), oneItemArray));

    }


    /**
     * Tests the toArray method on a list with items in it
     */
    public void testToArrayContents() {

        Object[] origArray = { "soccer", "swimming", "gymnastics" };
        assertTrue(Arrays.equals(smallListA.toArray(), origArray));
        assertTrue(Arrays.equals(emptyListA.toArray(), emptyListB.toArray()));
        assertFalse(Arrays.equals(smallListA.toArray(), bigListB.toArray()));

    }
    
	@Test
	public void addThrowsException() {

		Exception thrown = null;
		try {
			emptyListA.add(null);
		} catch (IllegalArgumentException exception) {
			thrown = exception;
		}

		// checks whether an Exception was actually thrown
		assertNotNull(thrown);

		// checks whether the right type of Exception was thrown
		assertTrue(thrown instanceof IllegalArgumentException);
	}
	
	@Test
	public void addToIndexThrowsException() {
		
		Exception thrown = null;
		try {
			emptyListA.add(0,null);
		} catch (IllegalArgumentException exception) {
			thrown = exception;
		}
		
		// checks whether an Exception was actually thrown
		assertNotNull(thrown);
		
		// checks whether the right type of Exception was thrown
		assertTrue(thrown instanceof IllegalArgumentException);
		
		thrown = null;
		try {
			emptyListA.add(-1,"Anish");
		} catch (IndexOutOfBoundsException exception) {
			thrown = exception;
		}
		
		// checks whether an Exception was actually thrown
		assertNotNull(thrown);
		
		// checks whether the right type of Exception was thrown
		assertTrue(thrown instanceof IndexOutOfBoundsException);
	}
	
	@Test
	public void addToIndex() {
		int count  = emptyListA.size();
		emptyListA.add(0,"Anish");
		
		assertEquals(count+1, emptyListA.size());
	}
	
//	@Test(timeout = 1000000)
//	public void addToIndex2() {
//		emptyListA = new SinglyLinkedList<String>();
//		emptyListA.add("Anish");
//		emptyListA.add("Manikonda");
//		emptyListA.add("Gary");
//		emptyListA.add(1,"Juan");
//		int count = emptyListA.size();
//		assertEquals(count, emptyListA.size());
//	}

	@Test
	public void removeTest() {
		emptyListA.add("Anish");
		emptyListA.add("Anish2");
		emptyListA.add("Anish3");

		emptyListA.add("Anish4");
		assertEquals(true, emptyListA.remove("Anish"));
		assertEquals("{Anish2, Anish3, Anish4}", emptyListA.toString());
		assertEquals(0, emptyListA.lastIndexOf("Anish2"));
	}
	
	@Test
	public void clearThrowsException() {

		emptyListA = new SinglyLinkedList<String>();
		Exception thrown = null;
		try {
			emptyListA.clear();
		} catch (Exception exception) {
			thrown = exception;
		}

		// checks whether an Exception was actually thrown
		assertNotNull(thrown);

		// checks whether the right type of Exception was thrown
		assertTrue(thrown instanceof IndexOutOfBoundsException);
	}

	@Test
	public void removeontainsTest() {
		emptyListA.add("Anish");
		emptyListA.add("Anish2");
		emptyListA.add("Anish3");
		emptyListA.add("Anish4");
		assertEquals(false,emptyListA.contains("Manikonda"));
		assertEquals(true,emptyListA.contains("Anish"));
	}
	

	@Test
	public void getTest() {
		emptyListA.add("Anish");
		emptyListA.add("Anish2");
		emptyListA.add("Anish3");
		emptyListA.add("Anish4");

		assertEquals("Anish", emptyListA.get(0));
		assertEquals("Anish2", emptyListA.get(1));
		assertEquals(false, emptyListA.remove("Check"));
	}
	
	@Test
	public void getThrowsException() {

		emptyListA = new SinglyLinkedList<String>();
		emptyListA.add("Anish");
		emptyListA.add("Anish2");
		
		Exception thrown = null;
		try {
			emptyListA.get(5);
		} catch (Exception exception) {
			thrown = exception;
		}

		// checks whether an Exception was actually thrown
		assertNotNull(thrown);

		// checks whether the right type of Exception was thrown
		assertTrue(thrown instanceof IndexOutOfBoundsException);
	}
	
	@Test
	public void removeThrowsException() {

		emptyListA = new SinglyLinkedList<String>();
		emptyListA.add("Anish");
		emptyListA.add("Anish2");
		
		Exception thrown = null;
		try {
			emptyListA.remove(-1);
		} catch (Exception exception) {
			thrown = exception;
		}

		// checks whether an Exception was actually thrown
		assertNotNull(thrown);

		// checks whether the right type of Exception was thrown
		assertTrue(thrown instanceof IndexOutOfBoundsException);
	}

	@Test
	public void removeIndexTest() {
		emptyListA.add("Anish");
		emptyListA.add("Anish2");
		emptyListA.add("Anish3");
		emptyListA.add("Anish4");

		assertEquals(true, emptyListA.remove(3));
	}
	
	@Test
	public void removeThrowsException2() {

		emptyListA = new SinglyLinkedList<String>();
		emptyListA.add("Anish");
		emptyListA.add("Anish2");
		
		Exception thrown = null;
		try {
			emptyListA.remove(4);
		} catch (Exception exception) {
			thrown = exception;
		}

		// checks whether an Exception was actually thrown
		assertNotNull(thrown);

		// checks whether the right type of Exception was thrown
		assertTrue(thrown instanceof IndexOutOfBoundsException);
	}
}
