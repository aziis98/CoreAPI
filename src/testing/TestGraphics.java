package testing;

import com.aziis98.dare.*;
import com.aziis98.dare.SwingWindow.*;
import com.aziis98.dare.inputs.*;
import com.aziis98.dare.math.*;

import java.awt.*;
import java.awt.event.*;

public class TestGraphics extends ApplicationCore {

    public static void main(String[] args) {
        launch(new TestGraphics());
    }

    QuadTree<Vector2f> vectors;

    @Override
    public void init() {
        WindowData.resizable = true;
        WindowData.title = "DareAPI Test";
        WindowData.pixelScale = 1F;

        vectors = new QuadTree<>(WindowData.getCenter(), WindowData.getHeight() / 16, WindowData.getHeight() / 16);

        Mouse.addClickListener(() -> {
            vectors.addNode(Mouse.getPosition());
        });

        Keyboard.key(KeyEvent.VK_R).addChangeListner(value -> {
            System.out.println("test " + value);
            if (value)
            {
                vectors = new QuadTree<>(WindowData.getCenter(), WindowData.getHeight() / 16, WindowData.getHeight() / 16);
            }
        });

        // Time.timer(1000, () -> System.out.println(vectors.querryRange(new BBox(WindowData.getCenter(), WindowData.width / 2, WindowData.height / 2)).size()));
    }

    @Override
    public void update() {
        for (Vector2f vector2f : vectors.querryAll())
        {
            vectors.remove(vector2f);
            vector2f.Add(new Vector2f(0.1F, 0));
            vectors.addNode(vector2f);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, WindowData.width, WindowData.height);

        QuadTree.debugQuadTree(g, vectors);
        vectors.forEachExternalBranch(tree -> {
            if (tree.contains(Mouse.getPosition()))
            {
                g.setColor(Color.YELLOW);
                QuadTree.drawRect(g, tree);
            }
        });
    }

}
