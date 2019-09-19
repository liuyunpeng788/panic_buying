package com.fosun.person.panicbuying;

import com.fosun.person.panicbuying.domain.ProductStore;
import com.fosun.person.panicbuying.mapper.ProductStoreMapper;
import com.fosun.person.panicbuying.service.OrderProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PanicBuyingApplicationTests {

    @Autowired
    private ProductStoreMapper productStoreMapper;

    @Autowired
    private OrderProductService orderProductService;

    @Test
    public void order(){
        int update = orderProductService.update(3, 3);
        System.out.println("update.." + update);
    }
    @Test
    public void contextLoads() {
        ProductStore store = new ProductStore();
        store.setName("test2");
        store.setNum(20);
        store.setVersion(1);
        productStoreMapper.insert(store);
    }

    /**
     * 采用乐观锁，重试3次进行更新
     */
    @Test
    public void updateStoreNumByThread(){
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                int tries = 3;
                ProductStore threadStore = productStoreMapper.findById(1);
                System.out.println( threadStore);
                while(tries >0){
                    threadStore.setNum(threadStore.getNum()+ 1);
                    int res = productStoreMapper.updateEntity(threadStore);
                    if(res >0){break;}
                    threadStore = productStoreMapper.findById(threadStore.getId());
                    --tries;
                    System.out.println("res=" + res + "--" + threadStore);
                    System.out.println("tryies-- now,try : " + tries);

                }
                if(tries == 0){
                    System.out.println( Thread.currentThread().getName() + " update failure after try 3 times...");
                }
            },"thread-" + i).start();

        }
        try {
            //休眠一段时间，让线程池有足够的时间来处理
            Thread.sleep(10000);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 采用ExecutorService 线程池 + 乐观锁 来处理。但是需要注意的是，如果不在future后添加get()操作，
     * 则会出现主函数执行完成，系统自动回收资源的情况。注意：在项目中，因为程序一直运行，所以不会出现
     * 数据库连接池因为主程序退出而自动回收资源的情况
     * 适用于订单处理
     * @throws Exception
     */
    @Test
    public void updateStoreNum() throws Exception{
        ExecutorService executorService = new ThreadPoolExecutor(3,10,10,
                TimeUnit.MINUTES,new LinkedBlockingQueue<>(100));

        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call(){
                    int tries = 3;
                    ProductStore threadStore = productStoreMapper.findById(1);
                    while(tries >0){
                        threadStore.setNum(threadStore.getNum()+ 1);
                        int res = productStoreMapper.updateEntity(threadStore);
                        if(res >0){
                            System.out.println(Thread.currentThread().getName() + " execute success, res=" + res);
                            return res;
                        }
                        threadStore = productStoreMapper.findById(threadStore.getId());
                        --tries;
                    }
                    if(tries == 0){
                        System.out.println( Thread.currentThread().getName() + " update failure after try 3 times...");
                    }
                    return 0;
                }
            });
            futureList.add(future);
        }
        futureList.forEach(x->{
            while(!x.isDone()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();

    }

}
