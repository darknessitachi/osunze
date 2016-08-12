// 将输入的数字金额转换成对应的中文大写金额
// idNumber输入的数字金额，idCHN输出的中文大写金额
function TransformNumberIntoCHN(idNumber, idCHN) {
    var number = parseInt(idNumber);
    if(number!="0.00"){
	    var unit = "仟佰拾亿仟佰拾万仟佰拾元角分", str = "";
	    number += "00";
	    var point = number.indexOf('.');
	    if (point >= 0) {
	        number = number.substring(0, point) + number.substr(point + 1, 2);
	    }
	    unit = unit.substr(unit.length - number.length);
	    for (var i = 0; i < number.length; i++) {
	        str += '零壹贰叁肆伍陆柒捌玖'.charAt(number.charAt(i)) + unit.charAt(i);
	    }
	    var str2 = str.replace(/零(仟|佰|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿) 万 |( 拾 )/g, "$1$2").replace(/^元 零?| 零 分/g, "").replace(/元$/g, "元整");
	    document.getElementById(idCHN).innerText="￥"+str2;
    }
}

/*
 * 将数据转成金额格式
 */
function fmoney(s)   
{
   var n=2;
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   return "￥"+t.split("").reverse().join("") + "." + r;
} 