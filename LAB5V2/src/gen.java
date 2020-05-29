import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.Arrays;


public class gen {

    int [] Array;
    int [] controlArr = new int[1];

    public  int [] Arr(int size){
        Array = new int[size];
        while (Array[0]==0){
            int number = rand(size);
            int counter = size-1;
            while (Array[counter]>number){
                counter--;
            }
                while ((counter>=0)&&(Array[counter]<number)){
                    int x = Array[counter];
                    Array[counter]= number;
                    number = x;
                    counter--;
                }
        }
        add_el(0,Array.length-1,0);
        System.out.println();
        show(controlArr,controlArr.length);
        rebuild();
            return Array;
    }

    public  int rand (int size){
        int number;
        number = (int)(1+Math.random()*(40+(size*2)));
        return number;
    }


    public  static void show(int [] arr, int size ){
        for (int i = 0; i< size; i++ ){

            System.out.print("[ "+arr[i]+" ] ");

            double xx = (Math.log(i+2)/Math.log(2));
            if ((xx)-Math.round(xx)==0) {
                System.out.println();
            }
        }

    }


    public int ind_c(int index, int S){
        if (S==1) { return (index*2)+1;}
        else { return (index*2)+2;}
    }

    public void Add(int pos, int num){
        if (pos>=controlArr.length){controlArr=Arrays.copyOf(controlArr,pos+1);}
        controlArr[pos]=num;
    }

    public void add_el(int min,int max, int pos){
        Add(pos,Array[(min+max)/2]);

        if (min<=(min+max)/2-1){
        add_el(min,((min+max)/2-1),ind_c(pos,1) );}
        if (min<max){
        add_el(((min+max)/2+1),max,ind_c(pos,2) );}

    }


    public void rebuild(){

        if (check_bin_s()==1) {
            System.out.println("BINARY SEARCH");
        }
        else System.out.println("NOT BINARY SEARCH");


        int max_level = (int)Math.ceil(Math.log(controlArr.length)/Math.log(2));

            if (controlArr[((int)Math.pow(2,(max_level-1)))]!=0){
            int x= controlArr[((int)Math.pow(2,(max_level-2))-1)];
            controlArr[((int)Math.pow(2,(max_level-2))-1)]= controlArr[((int)Math.pow(2,(max_level-1)))];
            controlArr[((int)Math.pow(2,(max_level-1)))] = x;
            }


        for (int i=2; i<max_level; i++){
            int end_el = ((int)Math.pow(2,i-1)-1)*2;
            int save = controlArr[end_el];
            for(int j=end_el; j>(end_el)/2;j--){
                controlArr[j]=controlArr[j-1];
            }
            controlArr[end_el/2]=save;
        }

        for (int i= 2; i<max_level;i++){
            int beg_el = (int)Math.pow(2,i-1)-1;
            int x = controlArr[beg_el];
            controlArr[beg_el]=controlArr[(beg_el*2)+2];
            controlArr[(beg_el*2)+2]=x;
            beg_el= beg_el*2+2;

            while (check_child(beg_el)!=0){
                if (check_child(beg_el)==1){
                    x = controlArr[beg_el];
                    controlArr[beg_el]=controlArr[(beg_el*2)+1];
                    controlArr[(beg_el*2)+1]=x;
                    beg_el= beg_el*2+1;
                } else {
                    x = controlArr[beg_el];
                    controlArr[beg_el]=controlArr[(beg_el*2)+2];
                    controlArr[(beg_el*2)+2]=x;
                    beg_el= beg_el*2+2;
                }
            }
        }
        System.out.println();

        if (controlArr[((int)Math.pow(2,(max_level-1))-1)]==0){max_level--;}
        if (controlArr[((int)Math.pow(2,(max_level-1))-1)]==0){
            controlArr[((int)Math.pow(2,(max_level-1))-1)]=controlArr[((int)Math.pow(2,(max_level-1)))];
            controlArr[((int)Math.pow(2,(max_level-1)))]=0;
            spusk(((int)Math.pow(2,(max_level-1))));
            max_level--;}

        for (int i= 1; i<=max_level/2;i++){
            int x = controlArr[((int)Math.pow(2,i-1)-1)];
            controlArr[((int)Math.pow(2,i-1)-1)] = controlArr[((int)Math.pow(2,(max_level-i))-1)];
            controlArr[((int)Math.pow(2,(max_level-i))-1)]= x;
        }
        spusk(((int)Math.pow(2,(max_level-1))-1));
        show(controlArr,controlArr.length);

        if (check_bin_s()==1) {
            System.out.println("BINARY SEARCH");
        }
        else System.out.println("NOT BINARY SEARCH");

    }

    public void spusk(int ind){
        while ((check_child(ind)!=0)&&((controlArr[(ind*2)+check_child(ind)]< controlArr[ind])||(controlArr[ind]==0))){
            if ((check_child(ind)==1)){
                int x= controlArr[ind];
                controlArr[ind]=controlArr[(ind*2)+1];
                controlArr[(ind*2)+1]= x;
            }else {
                int x= controlArr[ind];
                controlArr[ind]=controlArr[(ind*2)+2];
                controlArr[(ind*2)+2]= x;
            }

        }

    }

    public byte check_child(int index){
        int size = controlArr.length; byte i=0;
        if ((size>(index*2)+1) && (controlArr[(index*2)+1]!=0)) { i += 1; return i;}
        if ((size>(index*2)+2) && (controlArr[(index*2)+2]!=0)) { i += 2; return i;}
        return i;
    }

    public byte check_bin_s(){
        byte mark=1;
        for (int i=1; i<controlArr.length;i=i+2){
            if (controlArr[i]>controlArr[parent(i)]) {mark=0; i=controlArr.length;}else
                if ((controlArr[i+1]<controlArr[parent(i+1)])&&(controlArr[i+1]!=0)) {mark=0; i=controlArr.length;}

        }
        return mark;
    }
    public int parent(int pos){
        return ((pos-1)/2);
    }
}
