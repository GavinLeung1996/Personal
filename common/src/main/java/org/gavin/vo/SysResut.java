package org.gavin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysResut {
    private Integer status = 200 ;
    private String msg;
    private Object Data;


    public static SysResut success(){
        return new SysResut(200,"调用成功",null);
    }
     public static SysResut success(Object data){
        return new SysResut(200,"调用成功",data);
    }
    public static SysResut success(String msg,Object data){
        return new SysResut(200,msg,data);
    }

    public static SysResut fail(){
        return new SysResut(201,"调用失败",null);
    }

}
