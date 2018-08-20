package com.javase.type_enum;

import org.junit.Test;

/**
 * 玩转枚举类型
 */
public class enum_test2018 {

    @Test
    public void test_0722(){
        for (int i=0;i<10;i++){
            WebApplicationType webApplicationType = this.deduceWebApplicationType(i);
            switch (webApplicationType.ordinal()) {
                case 1:
                    System.out.println(webApplicationType.ordinal()+":" + webApplicationType);
                    break;
                case 2:
                    System.out.println(webApplicationType.ordinal()+":" + webApplicationType);
                    break;
                default:
                    System.out.println(webApplicationType.ordinal()+":" + webApplicationType);
                    break;
            }
        }

    }
    private WebApplicationType deduceWebApplicationType(int i) {
           switch (i){
               case 0:return WebApplicationType.E0;
               case 1:return WebApplicationType.E1;
               case 2:return WebApplicationType.E2;
               case 3:return WebApplicationType.E3;
               case 4:return WebApplicationType.E4;
               case 5:return WebApplicationType.E5;
               case 6:return WebApplicationType.E6;
               case 7:return WebApplicationType.E7;
               case 8:return WebApplicationType.E8;
               default:return WebApplicationType.E9;
           }


    }

}


enum WebApplicationType {
    E0,E1,E2,E3,E4,E5,
    E6,E7,E8,E9;

    private WebApplicationType() {
    }
}