package xmu.crms.entity;

public class Proportions {
	private int report;
	private int presentation;
	private int c;
	private int b;
	private int a;
	public Proportions(int report, int presentation, int c, int b, int a) {
		super();
		this.report = report;
		this.presentation = presentation;
		this.c = c;
		this.b = b;
		this.a = a;
	}
	public int getReport() {
		return report;
	}
	public void setReport(int report) {
		this.report = report;
	}
	public int getPresentation() {
		return presentation;
	}
	public void setPresentation(int presentation) {
		this.presentation = presentation;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
	
}
