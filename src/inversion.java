public class inversion {


    public static void main(String[] args) {

        int[] arr= {4,3,2,1};
        System.out.println(merge(arr,0,3));


    }

    public static int merge(int[]a, int p, int r){

        int ans=0;
        int q=Math.floorDiv(p+r,2);
        int left=merge(a,p,q);
        int right=merge(a,q+1,r);
         ans=inversion(a,p,q,r)+left+right;
         return ans;

    }

    public static int inversion(int[]a,int p,int q,int r){
        int ans=0;
        int n1=q-p+1;
        int n2=r-q;
        int[] left=new int[n1+1];
        int[] right=new int[n2+1];

        for (int i=0;i<n1;i++){
            left[i]=a[p+i-1];
        }
        for(int j=0;j<n2;j++){
            right[j]=a[q+j];
        }
        left[n1]=Integer.MIN_VALUE;
        right[n2]=Integer.MIN_VALUE;
        int i=0,j=1;
        for(int k=0;k<p+r;k++){
            if(right[j]<left[i]){
                ans++;
                j++;
            }else{
                i++;
            }

        }
        return ans;

    }
}
