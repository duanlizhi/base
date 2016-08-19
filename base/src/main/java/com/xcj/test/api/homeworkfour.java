package com.xcj.test.api;

import static java.lang.Math.E;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

import java.io.IOException;
import java.util.Timer;

public class homeworkfour {

	// 0~1区间n等分
	private static int n = 100000;

	// 随便定义个曲线e的x次方, 取其x在0~1的定积分;
	public static double f(double x) {
		double f;
		f = pow(E, x);
		return f;
	}

	// 梯形法求定积分
	/**
	 * x0: 坐标下限, xn: 坐标上限
	 */
	public static double getDefiniteIntegralByTrapezium(double x0, double xn) {
		double h = abs(xn - x0) / n;
		double sum = 0;
		for (double xi = 0; xi <= xn; xi = xi + h) {
			sum += (f(xi) + f(xi + h)) * h / 2;
		}
		return sum;
	}

	/**
	 * x0: 坐标下限, xn: 坐标上限
	 */
	// 矩形法求定积分, 右边界
	public static double getDefiniteIntegralByRectangle1(double x0, double xn) {
		//h: 步长
		double h = abs(xn - x0) / n;
		double sum = 0;
		for (double xi = 0; xi <= xn; xi = xi + h) {
			sum += f(xi + h) * h;
		}
		return sum;
	}

	// 矩形法求定积分, 左边界
	public static double getDefiniteIntegralByRectangle2(double x0, double xn) {
		double h = abs(xn - x0) / n;
		double sum = 0;
		for (double xi = 0; xi <= xn; xi = xi + h) {
			sum += f(xi) * h;
		}
		return sum;
	}
	public static Integer count=0;
	public static void getSum(Integer ss){
		count+=ss;
		System.out.println(count);
	}
	/**
	 * 测试定积分
	 */
	public static void main(String[] args) {
		 Timer timer = new Timer();
	        timer.schedule(new MyTask(), 10001, 1);// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
	        while (true) {// 这个是用来停止此任务的,否则就一直循环执行此任务了
	            try {
	                int ch = System.in.read();
	                if (ch - 'c' == 0) {
	                    timer.cancel();// 使用这个方法退出任务
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		
	}
	static class MyTask extends java.util.TimerTask {
        @Override
        public void run() {
        	getSum(5);
        	//System.out.println(getDefiniteIntegralByTrapezium(0, 1));
        }
    }
	
	/*while (true) {
		System.out.println(getDefiniteIntegralByTrapezium(0, 1));
		System.out.println(getDefiniteIntegralByRectangle1(0, 1));
		System.out.println(getDefiniteIntegralByRectangle2(0, 1));
		getSum(1);
	}*/

}