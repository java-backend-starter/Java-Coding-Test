import java.io.*;
import java.util.StringTokenizer;

public class Sort5 {
    static int quickSelect(int [] values, int start, int end, int k){
        if(start == end){
            return values[start];
        }
        int pivot = partition(values, start, end);
        if(k == pivot){
            return values[k];
        }
        else if(k < pivot){
            return quickSelect(values, start, pivot-1, k);
        }
        else {
            return quickSelect(values, pivot+1, end, k);
        }
    }

    static int partition(int [] values, int start, int end){
        int pivot = values[end];
        int left = start-1;
        for(int right = start; right < end; right++){
            if(values[right] < pivot){
                swap(values, ++left, right);
            }
        }
        swap(values, left+1, end);
        return left+1;
    }

    static void swap(int [] values, int i, int j){
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int [] values = new int[size];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++){
            values[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(quickSelect(values, 0, size-1, k-1));
    }
}