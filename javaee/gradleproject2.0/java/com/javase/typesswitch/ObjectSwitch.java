package com.javase.typesswitch;

/*
 * 对象类型强制转换
 *
 *
 * */

public  class ObjectSwitch {
	public static void main(String[] args){
		FatherClass subClass=new SubClass();
		FatherClass subClass_subClass=new SubClass_SubClass();
		//SubClass_SubClass subClass_subClass_switch= (SubClass_SubClass) subClass;//父类转化为子类不可以
		SubClass subClass_switch= (SubClass) subClass_subClass;//子类转化为父类
		System.out.println("父类是子类不对:"+(subClass instanceof  SubClass_SubClass));
		System.out.println("子类是父类正确:"+(subClass_subClass instanceof  SubClass));

		FatherClass fatherClass=new FatherClass();
		SubClass subClass2=new SubClass();
		FatherClass fatherClass_swtich=(FatherClass)subClass2;
		//SubClass subClass_switch2= (SubClass) fatherClass;//父类转化为子类不可以




	}
}
class SubClass_SubClass extends SubClass{


}

class SubClass extends FatherClass {
	
}

class FatherClass {

}

