package cn.duanshaojie.enumerate;

public enum AcountStatusEnum {
	NORMAL("正常",10),NOTACTIVE("未激活",20),FROZEN("冻结",30);
	
	private String name;
	private int index;
	
	private AcountStatusEnum(String name,int index){
		this.name = name;
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
