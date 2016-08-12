package com.skycity.framework;


public class Constant {
	public static final String ALGORITHM="DESede";				//加密算法,可用 DES,DESede,Blowfish
	
	public static final String CODE_BRAND="Brand";				//品牌代码
	public static final String CODE_GOODSTYPE="GoodsType";		//商品类别
	public static final String CODE_UNIT = "Unit";				//单位
	public static final String CODE_COLOR = "Color";			//颜色
	public static final String CODE_LEVEL = "Level";			//商品等级
	public static final String CODE_YESORNO = "YesOrNo";		//是否
	public static final String CODE_SEX = "Sex";				//性别
	

	public static final String SESSION_MENULIST = "menuList";			//通用功能关联的菜单
	public static final int USER_FORBIDDEN = -1;		//用户禁用
	public static final int USER_LOCK = 0;				//用户被锁定
	
	public static enum ORDERSTATUS {		//订单状态
		Initial("0", "订单创建"), DownPayment("1", "已交首付款"),
			Prepare("2", "已交备料款"),Finish("3", "订单完成");
        private String name;
        private String value;

        private ORDERSTATUS(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public static String getValue(String name){
        	 for (ORDERSTATUS c : ORDERSTATUS.values()){
        		 if(name.equalsIgnoreCase(c.getName())){
        			 return c.value;
        		 }
        	 }
        	 return "";
        }
        	 
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
