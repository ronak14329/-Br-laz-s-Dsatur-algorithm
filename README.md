# Myers-algorithm
Edit Distance

1.For the best Dsatur implementation, we can use two levels of priority bucket queues to select the next node to color. The first level consists of one priority queue in which thesaturation degree of the vertices is used as the key. In the secondary level, there isanother priority queue for every bucket in the first level’s queue. The secondarypriority queues are maintained to contain the same elements as the correspondingbucket in the primary bucket queue but with the degree of vertices in the uncoloredsubgraph as the key.
Dsatur first initializes a vector adjacentcolors to contain an empty set forevery vertex. This data structure is used to update the saturation degree of thenodes in constant time. Next all nodes are inserted into the primary bucket priorityqueue, which has max{d(v) | v ∈V}buckets using the initial saturation degree(zero) as the key. Every vertex is also inserted into the secondary bucket queuecorresponding to the first bucket of the primary bucket queue with the initial degreein the uncolored subgraph (d(v)) as the key. This runs in θ (|V |) time.Until the primary priority queue is empty, a vertex with a maximum saturationdegree amongst all vertices is chosen using the primary priority queue. A tie isresolved by choosing a vertex with the largest degree in the uncolored subgraph usingthe secondary level of priority queues. Next the chosen node is removed from thepriority queues, colored using the lowest available color and the saturation degree ofall adjacent nodes is updated. This is done by checking for every neighboring vertexif the corresponding entry in its adjacentcolors array is set and if not, setting theentry and moving the vertex to the corresponding bucket.


2 The running time complexity of our Dsatur algorithm is O|V|2.Since we have implemented the priority queue which reduces the time complexity from 
(|𝑉|2⋅|𝐸|) to O|V|2.
3-
Graph shows that the running time increases with the increase in the number of vertices and it also is directly proportional to the density of the graph.Higher the density of the graph, more edges to traverse and it takes more time.


Step to run the TCSS543A.java
1. Run the TCSS543A.java
2. Enter the File location where you want to save the random graphs.
