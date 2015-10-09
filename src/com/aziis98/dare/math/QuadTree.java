package com.aziis98.dare.math;

import com.aziis98.dare.interfaces.*;
import com.aziis98.dare.util.*;

import java.awt.*;
import java.util.function.*;

public class QuadTree<T extends IPos2f> extends BBox {

    private QuadTree<T> top_left;
    private QuadTree<T> top_right;
    private QuadTree<T> bottom_left;
    private QuadTree<T> bottom_right;

    private T node;

    public QuadTree(Vector2f center, float width, float height) {
        super(center, width, height);
    }

    public QuadTree(float cx, float cy, float halfWidth, float halfHeight) {
        super(cx, cy, halfWidth, halfHeight);
    }

    public boolean hasNode() {
        return node != null;
    }

    public boolean isSplitted() {
        return top_left != null;
    }

    public T getNode() {
        return node;
    }

    public void addNode(T node) {
        if (!contains(node))
        {
            expandTree(node);
            return;
        }

        if (!isSplitted() && !hasNode())
        {
            this.node = node;
            return;
        }

        splitTree();

        if (top_left.contains(node))
        {
            top_left.addNode(node);
        }
        else if (top_right.contains(node))
        {
            top_right.addNode(node);
        }
        else if (bottom_left.contains(node))
        {
            bottom_left.addNode(node);
        }
        else if (bottom_right.contains(node))
        {
            bottom_right.addNode(node);
        }
    }

    private void expandTree(T node) {
        if (node.getX() > this.getCenterX())
        {
            if (node.getY() > this.getCenterY()) // Bottom Right
            {
                QuadTree<T> newtop_left = new QuadTree<>(getCenterX(), getCenterY(), getHalfWidth(), getHalfHeight());
                newtop_left.node = this.node;
                this.node = null;
                newtop_left.top_left = this.top_left;
                newtop_left.top_right = this.top_right;
                newtop_left.bottom_left = this.bottom_left;
                newtop_left.bottom_right = this.bottom_right;

                top_left = newtop_left;

                this.top_right = new QuadTree<>(getCenterX() + getWidth(), getCenterY(), getHalfWidth(), getHalfHeight());
                this.bottom_left = new QuadTree<>(getCenterX(), getCenterY() + getHeight(), getHalfWidth(), getHalfHeight());
                this.bottom_right = new QuadTree<>(getCenterX() + getWidth(), getCenterY() + getHeight(), getHalfWidth(), getHalfHeight());

                this.set(getCenterX() + getHalfWidth(), getCenterY() + getHalfHeight(), getWidth(), getHeight());
            }
            else // Top Right
            {
                QuadTree<T> newbottom_left = new QuadTree<>(getCenterX(), getCenterY(), getHalfWidth(), getHalfHeight());
                newbottom_left.node = this.node;
                this.node = null;
                newbottom_left.top_left = this.top_left;
                newbottom_left.top_right = this.top_right;
                newbottom_left.bottom_left = this.bottom_left;
                newbottom_left.bottom_right = this.bottom_right;

                bottom_left = newbottom_left;

                this.top_left = new QuadTree<>(getCenterX(), getCenterY() - getHeight(), getHalfWidth(), getHalfHeight());
                this.top_right = new QuadTree<>(getCenterX() + getWidth(), getCenterY() - getHeight(), getHalfWidth(), getHalfHeight());
                this.bottom_right = new QuadTree<>(getCenterX() + getWidth(), getCenterY(), getHalfWidth(), getHalfHeight());

                this.set(getCenterX() + getHalfWidth(), getCenterY() - getHalfHeight(), getWidth(), getHeight());
            }
        }
        else
        {
            if (node.getY() > this.getCenterY()) // Bottom Left
            {
                QuadTree<T> newtop_right = new QuadTree<>(getCenterX(), getCenterY(), getHalfWidth(), getHalfHeight());
                newtop_right.node = this.node;
                this.node = null;
                newtop_right.top_left = this.top_left;
                newtop_right.top_right = this.top_right;
                newtop_right.bottom_left = this.bottom_left;
                newtop_right.bottom_right = this.bottom_right;

                top_right = newtop_right;

                this.top_left = new QuadTree<>(getCenterX() - getWidth(), getCenterY(), getHalfWidth(), getHalfHeight());
                this.bottom_left = new QuadTree<>(getCenterX() - getWidth(), getCenterY() + getHeight(), getHalfWidth(), getHalfHeight());
                this.bottom_right = new QuadTree<>(getCenterX(), getCenterY() + getHeight(), getHalfWidth(), getHalfHeight());

                this.set(getCenterX() - getHalfWidth(), getCenterY() + getHalfHeight(), getWidth(), getHeight());
            }
            else // Top Left
            {
                QuadTree<T> newbottom_right = new QuadTree<>(getCenterX(), getCenterY(), getHalfWidth(), getHalfHeight());
                newbottom_right.node = this.node;
                this.node = null;
                newbottom_right.top_left = this.top_left;
                newbottom_right.top_right = this.top_right;
                newbottom_right.bottom_left = this.bottom_left;
                newbottom_right.bottom_right = this.bottom_right;

                bottom_right = newbottom_right;

                this.top_left = new QuadTree<>(getCenterX() - getWidth(), getCenterY() - getHeight(), getHalfWidth(), getHalfHeight());
                this.top_right = new QuadTree<>(getCenterX(), getCenterY() - getHeight(), getHalfWidth(), getHalfHeight());
                this.bottom_left = new QuadTree<>(getCenterX() - getWidth(), getCenterY(), getHalfWidth(), getHalfHeight());

                this.set(getCenterX() - getHalfWidth(), getCenterY() - getHalfHeight(), getWidth(), getHeight());
            }
        }

        this.addNode(node);
    }

    public boolean remove(T node) {
        if (!contains(node)) return false;

        if (!isSplitted())
        {
            if (hasNode() && node.equals(this.node))
            {
                this.node = null;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return top_left.remove(node) || top_right.remove(node) || bottom_left.remove(node) || bottom_right.remove(node);
        }
    }

    private void splitTree() {
        if (isSplitted()) return;

        float hhw = getHalfWidth() / 2;
        float hhh = getHalfHeight() / 2;

        top_left = new QuadTree<>(getCenterX() - hhw, getCenterY() - hhh, hhw, hhh);
        top_right = new QuadTree<>(getCenterX() + hhw, getCenterY() - hhh, hhw, hhh);
        bottom_left = new QuadTree<>(getCenterX() - hhw, getCenterY() + hhh, hhw, hhh);
        bottom_right = new QuadTree<>(getCenterX() + hhw, getCenterY() + hhh, hhw, hhh);

        addNode(this.node);
        this.node = null;
    }

    public EList<T> querryAll() {
        return querryRange(this);
    }

    public EList<T> querryRange(BBox box) {
        EList<T> list = new EList<>();
        querryRange(box, list);
        return list;
    }

    private void querryRange(BBox box, EList<T> list) {
        if (!isSplitted())
        {
            if (hasNode() && box.contains(node.getX(), node.getY()))
            {
                list.add(node);
            }
            return;
        }

        top_left.querryRange(box, list);
        top_right.querryRange(box, list);
        bottom_left.querryRange(box, list);
        bottom_right.querryRange(box, list);
    }

    public void forEach(Consumer<T> consumer) {
        if (!isSplitted() && hasNode())
        {
            consumer.accept(node);
        }
        else if (isSplitted())
        {
            top_left.forEach(consumer);
            top_right.forEach(consumer);
            bottom_left.forEach(consumer);
            bottom_right.forEach(consumer);
        }
    }

    public void forEachBrach(Consumer<QuadTree<T>> consumer) {
        consumer.accept(this);

        if (isSplitted())
        {
            top_left.forEachBrach(consumer);
            top_right.forEachBrach(consumer);
            bottom_left.forEachBrach(consumer);
            bottom_right.forEachBrach(consumer);
        }
    }

    public void forEachExternalBranch(Consumer<QuadTree<T>> consumer) {
        if (!isSplitted())
        {
            consumer.accept(this);
        }
        else
        {
            top_left.forEachExternalBranch(consumer);
            top_right.forEachExternalBranch(consumer);
            bottom_left.forEachExternalBranch(consumer);
            bottom_right.forEachExternalBranch(consumer);
        }
    }


    public static <T extends IPos2f> void debugQuadTree(Graphics2D g, QuadTree<T> tree, Color rectangles, Color nodes) {
        tree.forEachBrach(subtree -> {
            g.setColor(rectangles);
            drawRect(g, subtree);
        });
        tree.forEach(node -> {
            g.setColor(nodes);
            g.drawOval((int) node.getX() - 2, (int) node.getY() - 2, 4, 4);
        });
    }

    public static <T extends IPos2f> void debugQuadTree(Graphics2D g, QuadTree<T> tree) {
        debugQuadTree(g, tree, Color.BLUE, Color.GREEN);
    }

    public static <T extends IPos2f> QuadTree<T> createTree(float x, float y, float width, float height) {
        return new QuadTree<>(x + width / 2, y + height / 2, width / 2, height / 2);
    }

    @Override
    public String toString() {
        return String.format("{: %s, node: %s, TL: %s, TR: %s, BL: %s, BR: %s}", super.toString(), node, top_left, top_right, bottom_left, bottom_right);
    }

    public static <T extends IPos2f> void drawRect(Graphics2D g, QuadTree<T> tree) {
        int border = 0;
        g.drawRect((int) tree.getX1() + border, (int) tree.getY1() + border, (int) tree.getWidth() - border * 2, (int) tree.getHeight() - border * 2);
    }

}
















































