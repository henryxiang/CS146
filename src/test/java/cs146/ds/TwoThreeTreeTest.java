package cs146.ds;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TwoThreeTreeTest {
    private TwoThreeTree tree;

    @Before
    public void init() {
        tree = new TwoThreeTree();
    }

    @Test
    public void testInserting() {
        TwoThreeTree.Node root = tree.getRoot();
        assertNull(root);

        tree.insert(1);
        root = tree.getRoot();
        assertNotNull(root);
        assertTrue(root.isTwoNode());

        tree.insert(2);
        root = tree.getRoot();
        assertTrue(root.isThreeNode());

        tree.insert(3);
        root = tree.getRoot();
        List<TwoThreeTree.Node> children = root.getChildren();
        assertTrue(root.isTwoNode());
        assertEquals(2, children.size());
        int key1 = children.get(0).getKeys().get(0);
        int key2 = children.get(1).getKeys().get(0);
        assertEquals(1, key1);
        assertEquals(3, key2);
    }

}