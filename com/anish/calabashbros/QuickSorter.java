package com.anish.calabashbros;



public class QuickSorter<T extends Comparable<T>> implements Sorter<T>{

    private T[] a;
    private String plan = "";

    public void load(T[] elements){
        this.a = elements;
    }

    public void sort(){
        quickSort(0, a.length - 1);
    }

    private void quickSort(int left, int right){
        if (left >= right){
            return;
        }
        int index = getStandardIndex(left, right);
        quickSort(left, index - 2);
        quickSort(index, right);
    }

    private int getStandardIndex(int left, int right){
        T standard = a[left];
        int index = left + 1;
        for (int i = index; i <= right; i++){
            if (a[i].compareTo(standard) < 0){
               
                swap(i, index);
                index++;
            }
        }
        swap(left, index - 1);
        return index;
    }



    public String getPlan(){
        return this.plan;
    }

    private void swap(int i, int j){
        T temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }
}
