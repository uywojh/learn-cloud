package com.learning.cloud.feign.hystrix;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class CommandHelloWorldObs extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorldObs(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ccblog"))  //监控使用
              .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey")) //
              .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolTest"))
              .andCommandPropertiesDefaults(
                  HystrixCommandProperties.Setter()
                      .withExecutionTimeoutInMilliseconds(500)
                      .withCircuitBreakerRequestVolumeThreshold(3)
                      .withCircuitBreakerErrorThresholdPercentage(75)
                      .withCircuitBreakerSleepWindowInMilliseconds(3)
              )
              .andThreadPoolPropertiesDefaults(
                  HystrixThreadPoolProperties.Setter()
                      .withCoreSize(100)    // 配置线程池里的线程数
              )
      );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
//        throw new RuntimeException("this command always fails");
    	Thread.sleep(1000);
        return "hello";
    }

    @Override
    protected String getFallback() {
        return "Hello Failure " + name + "!"+Thread.currentThread().getName();
    }
    
    public static void main(String[] args) throws Exception {
	       try {
	            for (int i = 0 ; i < 4; i++){
	                new Thread(new Runnable() {
	                    @Override
	                    public void run() {
	                        System.out.println(new CommandHelloWorldObs("").execute());
	                    }
	                }).start();
	                Thread.sleep(1000);
	            }
	            for (int i = 0 ; i < 4; i++){
	                new Thread(new Runnable() {
	                    @Override
	                    public void run() {
	                        System.out.println(new CommandHelloWorldObs("").execute());
	                    }
	                }).start();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	}
        
    }
}
