package com.aziis98.dare.math;

import com.aziis98.dare.interfaces.*;

public class OcTree<T extends IPos3f> {
/*
    private Vector3f center;
    private Vector3f halfSizes;

    private OcTree<T> oct000;
    private OcTree<T> oct001;
    private OcTree<T> oct101;
    private OcTree<T> oct100;
    private OcTree<T> oct010;
    private OcTree<T> oct011;
    private OcTree<T> oct110;
    private OcTree<T> oct111;

    private T node;

    public OcTree(Vector3f center, float width, float height, float depth) {
        this(center, new Vector3f(width / 2, height / 2, depth / 2));
    }

    public OcTree(Vector3f center, Vector3f halfSizes) {
        this.center = center;
        this.halfSizes = halfSizes;
    }

    public boolean hasNode() {
        return node != null;
    }

    public boolean isSplitted() {
        return oct000 != null;
    }

    public T getNode() {
        return node;
    }

    public void addNode(T node) {
        if (!isSplitted() && !hasNode())
        {
            this.node = node;
            return;
        }

        splitTree();

        if (oct000.contains(node))
        {
            oct000.addNode(node);
        }
        else if (oct001.contains(node))
        {
            oct001.addNode(node);
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

    public boolean contains(T node) {
        return Maths.inRange(center.x - halfSizes.x, node.getX(), center.x + halfSizes.x) &&
                Maths.inRange(center.y - halfSizes.y, node.getY(), center.y + halfSizes.y) &&
                Maths.inRange(center.z - halfSizes.z, node.getZ(), center.z + halfSizes.z);
    }

    public boolean remove(T node) {
        if (!contains(node)) return false;

        if (!isSplitted())
        {
            if (hasNode() && node.equals(this.node))
            {
                node = null;
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

        top_left = new OcTree<>(getCenterX() - hhw, getCenterY() - hhh, hhw, hhh);
        top_right = new OcTree<>(getCenterX() + hhw, getCenterY() - hhh, hhw, hhh);
        bottom_left = new OcTree<>(getCenterX() - hhw, getCenterY() + hhh, hhw, hhh);
        bottom_right = new OcTree<>(getCenterX() + hhw, getCenterY() + hhh, hhw, hhh);

        addNode(this.node);
        this.node = null;
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

    public void forEachBrach(Consumer<OcTree<T>> consumer) {
        consumer.accept(this);

        if (isSplitted())
        {
            top_left.forEachBrach(consumer);
            top_right.forEachBrach(consumer);
            bottom_left.forEachBrach(consumer);
            bottom_right.forEachBrach(consumer);
        }
    }

    public void forEachExternalBranch(Consumer<OcTree<T>> consumer) {
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


    public static <T extends IPos2f> void debugQuadTree(Graphics2D g, OcTree<T> tree, Color rectangles, Color nodes) {
        tree.forEachBrach(subtree -> {
            g.setColor(rectangles);
            drawRect(g, subtree);
        });
        tree.forEach(node -> {
            g.setColor(nodes);
            g.drawOval((int) node.getX() - 2, (int) node.getY() - 2, 4, 4);
        });
    }

    public static <T extends IPos2f> void debugQuadTree(Graphics2D g, OcTree<T> tree) {
        debugQuadTree(g, tree, Color.BLUE, Color.GREEN);
    }

    public static <T extends IPos2f> OcTree<T> createTree(float x, float y, float width, float height) {
        return new OcTree<>(x + width / 2, y + height / 2, width / 2, height / 2);
    }

    @Override
    public String toString() {
        return String.format("{: %s, node: %s, TL: %s, TR: %s, BL: %s, BR: %s}", super.toString(), node, top_left, top_right, bottom_left, bottom_right);
    }

    public static <T extends IPos2f> void drawRect(Graphics2D g, OcTree<T> tree) {
        g.drawRect((int) tree.getX1(), (int) tree.getY1(), (int) tree.getWidth(), (int) tree.getHeight());
    }
*/
}
















































