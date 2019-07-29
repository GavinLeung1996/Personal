package org.gavin.quartz;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.gavin.mapper.OrderMapper;
import org.gavin.pojo.Order;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Calendar;
import java.util.Date;

public class OrderQuartz extends QuartzJobBean {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,-30);
        Date timeOut = calendar.getTime();
        Order order = new Order();
        order.setStatus(6).setUpdated(new Date());
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("status",1).lt("created",timeOut);
        orderMapper.update(order,updateWrapper);
        System.out.println("定时任务完成 更新数据库");
    }
}
