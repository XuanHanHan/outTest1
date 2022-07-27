package org.optaplanner.examples.projectjobscheduling.domain.solver;

import java.util.Comparator;

public class DelayStrengthComparator implements Comparator<Integer> {

    /**
     * Compare（）比较用来排序的两个参数。根据第一个参数小于、等于或大于第二个参数分别返回负整数、零或正整数。
     * 在前面的描述中，符号 sgn(expression) 表示 signum 数学函数，
     * 根据 expression 的值为负数、0 还是正数，该函数分别返回 -1、0 或 1。
     * */
    @Override
    public int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }

}
