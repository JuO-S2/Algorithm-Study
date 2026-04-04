package Day260405.BOJ1916;

import java.io.*;
import java.util.*;

public class O {

    static BufferedReader br;
    static StringTokenizer st;
    static Node node[];
    static int distance[];

    public static void main(String[] args) throws IOException{


        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        node = new Node[N+1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            node[start] = new Node(end,weight,node[start]);
        }
        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[start] = 0;
        boolean visited[] = new boolean[N+1];

        int min;
        int currentIdx;

        PriorityQueue<Vertex> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight,o2.weight));

        pq.offer(new Vertex(distance[start],start));


        for(int i = 0; i < N; i++) {

            min = Integer.MAX_VALUE;

            Vertex v =pq.poll();
            if(v == null){
                break;
            }
            if(visited[v.no]){
                continue;
            }

            visited[v.no] = true;

            if(v.no == end){
                break;
            }

            for(Node n = node[v.no]; n!=null; n = n.next){
                if(!visited[n.vertex] && distance[n.vertex]>distance[v.no]+n.weight){
                    distance[n.vertex] = distance[v.no] + n.weight;
                    pq.offer(new Vertex( distance[n.vertex],n.vertex));
                }
            }


        }

        System.out.println(distance[end]);

    }
    static class Vertex{
        int weight;
        int no;

        Vertex(int weight, int no){
            this.weight = weight;
            this.no = no;
        }
    }

    static class Node {

        int vertex;
        int weight;
        Node next;

        Node(int vertex, int weight, Node next) {
            this.weight = weight;
            this.next = next;
            this.vertex = vertex;
        }

        @Override
        public String toString() {
            return this.vertex + " " + this.weight;
        }
    }

}
