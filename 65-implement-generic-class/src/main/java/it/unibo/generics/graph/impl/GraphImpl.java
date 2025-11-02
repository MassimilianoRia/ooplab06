package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    final private Map<N, Set<N>> nodes;

    public GraphImpl () {
        nodes = new HashMap<>();
    }

    @Override
    public void addNode(N node) {
        if (!containsNonNullNode(node)) {
            nodes.put(node, new HashSet<>());
        }
    }

    @Override
    public void addEdge(N source, N target) {
        if (containsNonNullNode(source) && containsNonNullNode(target)) {
            nodes.get(source).add(getMyReference(target));
        }
    }

    @Override
    public Set<N> nodeSet() {
        return new HashSet<>(nodes.keySet());
    }

    @Override
    public Set<N> linkedNodes(N node) {
        if (containsNonNullNode(node)) {
            return new HashSet<>(nodes.get(getMyReference(node)));
        }
        throw new IllegalArgumentException("Node is null or not contained in this graph");
    }

    //BFS
    @Override
    public List<N> getPath(N source, N target) {

        if (!containsNonNullNode(source) || !containsNonNullNode(target)) {
            throw new IllegalArgumentException("Source or target node is null or not contained in this graph");
        }

        final List<N> path = new ArrayList<>();
        final Map<N, N> parent = BFS(source);

        N pivot = getMyReference(target);

        while (Objects.nonNull(pivot)) {
            path.add(pivot);
            pivot = parent.get(pivot);
        }

        return path.reversed(); 

    }
    
    /**
     * Utility method that checks whether the given node is non-null and already present in the graph.
     *
     * @param node the node to verify
     * @return {@code true} if the node is not null and is contained in the graph
     */
    private boolean containsNonNullNode(N node) {
        return nodes.containsKey(node) && Objects.nonNull(node);
    }

    private N getMyReference(N node) {
        for (final var tmp : nodes.keySet()) {
            if (node.equals(tmp)) {
                return tmp;
            }
        }
        throw new IllegalStateException("Node not present in graph: " + node);
    }

    private Map<N, N> BFS (N source) {

        final Map<N, N> parent = new HashMap<>();
        final Map<N, String> color = new HashMap<>();
        final List<N> queue = new ArrayList<>();
        N currentNode = null;

        for (final N tmp : nodes.keySet()) {
            parent.put(tmp, null);
            color.put(tmp, (tmp.equals(source)) ? "GRAY" : "WHITE");
        }

        queue.addLast(source);

        while (!queue.isEmpty()) {
            currentNode = queue.removeFirst();
            for (final N tmp : nodes.get(currentNode)) {
                if (color.get(tmp).equals("WHITE")) {
                    color.put(tmp, "GRAY");
                    parent.put(tmp, currentNode);
                    queue.addLast(tmp);
                }
            }
            color.put(currentNode, "BLACK");
        }

        return parent;
    }

}
