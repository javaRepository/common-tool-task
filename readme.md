#common-tool-task------java工具类
## 应用场景
#### 保证多台应用水平扩展时，只有一个应用的定时任务执行。
(例如nginx前端，配置多台tomcat，每个tomcat里都有执行定时任务，数据可能会被重复处理，为保证同一时间只有一台tomcat在执行定时任务)

## 使用注意
- 保证每台应用服务器时间同步
- 每个任务类一个redisKey，且唯一
- time请设置为任务时间
 
## 方法介绍，作用相同
### TaskHelper
- isRun(Class<?> taskClazz)   过期时间单位为s
- isRun(String redisKey, long expireSecondTime)   过期时间单位为s
- isRunFastTask(String redisKey, long expireMillisecondTime)   过期时间单位为ms

### TaskHelperBo
- isRun(RedisTemplate<String, String> redisTemplate, String redisKey, long intervalTime)  过期时间单位为s   

## 提供了四个方法，作用相同，三个过期时间为s，一个为ms，配置步骤

## 使用步骤
### TaskHelper
- 该工具类需要交由spring容器管理，并注入RedisTemplate
```java
<bean class="com.tool.task.taskhandle.TaskHelper">
        <property name="redisTemplate" ref="redisTemplate"></property>
</bean>
```

- 在任务类中注入taskHelper

```java
@Autowired
private TaskHelper taskHelper;

//实现方式一,需要在job方法上用Task注解标识
@Scheduled(cron="0/10 * * * * ?")
@Task(redisKey="testTask", expireSecondTime = 10) //redisKey为set到redis的唯一key，expireTime单位为s
public void excute() {
    Boolean flag = taskHelper.isRun(TaskTest.class);   //参数为任务类的字节码
    if(flag) {
        System.out.println((new Date() + "task run"));
    }
}
//实现方式二，直接传入key和time
@Scheduled(cron="0/10 * * * * ?")
public void excute() {
    Boolean flag = taskHelper.isRun("testTask", 10); //redisKey为set到redis的唯一key，expireTime单位为s
    if(flag) {
        System.out.println((new Date() + "task run"));
    }
}
//该方法为1s的任务提供
@Scheduled(cron="0/1 * * * * ?")
public void excute4() {
    Boolean flag = taskHelper.isRunFastTask("testTask4", 1000);
    if(flag) {
        System.out.println((new Date() + " task4 run"));
    }
}
```

### TaskHelperBo使用步骤
- 该工具类提供了一个static方法，需要传入RedisTemplate
- 在任务类中注入RedisTemplate对象

```java
@Autowired
private RedisTemplate redisTemplate;

@Scheduled(cron="0/10 * * * * ?")
public void excute() {
    Boolean flag = TaskHelperBo.isRun(redisTemplate, "testTask", 10); // 第二个参数为rediskey，第三个key过期时间，单位s
    // true:允许执行; false:没有权限执行
    if(flag) {
        System.out.println((new Date() + "task run"));
    }
}
```

## 实现原理
#### 基于redis的SETNX命令，多个应用的每个任务向指定的key插入数据，插入成功，执行继续任务，失败不执行。
不管插入是否成功都会设置过期时间。过期时间在程序中处理为任务时间的一半。


## 参考github上说明：https://github.com/JavaServerGroup/jtool-task
   af项目PalmCall中用到了

更多工具类请看：https://github.com/JavaServerGroup