# 作用

  - 管理项目初始化，该模块不做依赖处理，仅仅处理插件和仓库设置

# versions-maven-plugin

  - 方便管理同意脚手架版本。
  - 当整体版本需要升级的时候，之前我们都是采用人工修改版本的方式，而通过versions-maven-plugin就可以轻松管理整体版本

![img.png](imgs/newVersion.png)

  - 如上图我们只需要执行`mvn versions:set -DnewVersion='1.1-SNAPSHOT'` 就可以轻松实现所有模块的修改包括所依赖的父项目版本

## mvn versions:set -DnewVersion='1.1-SNAPSHOT'

  - 修改统一版本

## mvn versions:display-dependency-updates

  - 查看那些者依赖有更新版本
![img_1.png](imgs/checkUpdates.png)

# mvn versions:display-plugin-updates

  - 查看那些插件有更新版本

# mvn versions:use-latest-versions

  - 将所有的依赖自动升级到最新版本(慎用)

# mvn versions:commit

  - 升级后如果满意我们就可以提交了

# mvn versions:revert

  - use-lastest-versions后不满意我们可以回退



# 规范约定
# 一、编码规范

## 1.1   命名规范

- 1、代码中的命名均不能以特殊字符（如下划线、$#符号）开始或结束。
    - 反例： _name / #Object

- 2、代码中的命名严禁使用拼音与英文混合的方式，更不允许直接使用中文的方式。
    - 反例： taobao/ renlei

- 3、类名使用UpperCamelCase风格，必须遵从驼峰形式。
    - 正例：CommonUtils / TestController

- 4、方法名、参数名、成员变量、局部变量都统一使用lowerCamelCase风格，必须遵从驼峰形式。
    - 正例： orderService / getOrderService()

- 5、常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。
    - 正例： USER_TABLE

- 6、抽象类命名使用Abstract或Base开头；异常类命名使用Exception结尾；测试类命名以它要测试的类的名称开始，以Test结尾。

- 7、包名使用com.hetai.服务名.分层名。
    - 正例： oauth系统的DAO， com.hetai.oauth.dao

- 8、如果使用到了设计模式或具有明确职责，建议在类名中体现出具体模式或职责。
    - 正例：ExecutorFactory / AbstractProducer
- 9、各分层都需要接口和实现类，实现类用Impl作后缀与接口区别。
    - 正例：OrderServiceImpl实现OrderService接口。
- 10、枚举类名建议带上Enum后缀，枚举成员名称需要全大写，单词间用下划线隔开。
    - 正例：枚举名字：PolicyIdTypeEnum，成员名称：ID_CARD/ PASSPORT。

- 11、各层方法命名规范：

    - 1） 查询的方法用get/ query做前缀。

    - 2） 插入的方法用add 或insert做前缀。

    - 3） 删除的方法用remove 或delete做前缀。

    - 4） 修改的方法用modify/ update做前缀。

    - 5） 获取多个对象的方法用List做结尾。

    - 6）  获取统计值的方法用Count做结尾。

- 12、全局常量类名建议“模块名Constant”。
    - 正例：枚举名字：OrderConstant / CommonConstants。
- 13、不要出现任何魔法值（即未经定义的常量）直接出现在代码中，应作常量声明。
    - 反例： return "UNDERWRITE_RESULT_" + order_no;

## 1.2   OOP规范

- 1、对外暴露的接口签名，原则上不允许修改方法签名，避免对接口调用方产生影响。

- 2、序列化类新增属性时，请不要修改serialVersionUID字段，避免反序列失败；如果完全不兼容升级，避免反序列化混乱，那么请修改serialVersionUID值。
    - 说明：注意serialVersionUID不一致会抛出序列化运行时异常。

- 3、POJO类必须写toString方法。使用IDE的中工具：source> generate toString时，如果继承了另一个POJO类，注意在前面加一下super.toString。
    - 说明：在方法执行抛出异常时，可以直接调用POJO的toString()方法打印其属性值，便于排查问题。

- 4、当一个类有多个构造方法，或者多个同名方法，这些方法应该按顺序放置在一起，便于阅读。

- 5、POJO类中变量需要有get/set方法，通过使用IDE Generate自动生成。

- 6、循环体内字符串的联接方式，使用StringBuilder的append方法进行扩展。

- 7、final可提高程序响应效率，声明成final的情况：

    - 1） 不需要重新赋值的变量，包括类属性、局部变量。

    - 2） 对象参数前加final，表示不允许修改引用的指向。

    - 3） 类方法确定不允许被重写。

- 8、慎用Object的clone方法来拷贝对象。 说明：对象的clone方法默认是浅拷贝，若想实现深拷贝需要重写clone方法实现属性对象的拷贝

- 9、不要在foreach循环里进行元素的remove/add操作。remove元素请使用Iterator方式，如果并发操作，需要对Iterator对象加锁

- 10、使用entrySet遍历Map类集合KV，而不是keySet方式进行遍历。

    - 说明：keySet其实是遍历了2次，一次是转为Iterator对象，另一次是从hashMap中取出key所对应的value。
    - 而entrySet只是遍历了一次就把key和value都放到了entry中，效率更高。
    - 如果是JDK8，使用Map.foreach方法。

    - 正例：values()返回的是V值集合，是一个list集合对象；keySet()返回的是K值集合，是一个Set集合对象；entrySet()返回的是K-V值组合集合

- 11、高度注意Map类集合K/V能不能存储null值的情况，如下表格：

| **集合类**        | **Key**      | **Value**    | **Super**   | **说明**   |
| ----------------- | ------------ | ------------ | ----------- | ---------- |
| Hashtable         | 不允许为null | 不允许为null | Dictionary  | 线程安全   |
| ConcurrentHashMap | 不允许为null | 不允许为null | AbstractMap | 分段锁技术 |
| TreeMap           | 不允许为null | 允许为null   | AbstractMap | 线程不安全 |
| HashMap           | 允许为null   | 允许为null   | AbstractMap | 线程不安全 |



## 1.3   控制规范

- 1、在一个switch块内，每个case要么通过break/return等来终止，要么注释说明程序将继续执行到哪一个case为止;在一个switch块内，都必须包含一个default语句并且放在最后，即使它什么代码也没有。

- 2、在if/else/for/while/do语句中必须使用大括号，即使只有一行代码，避免使用下面的形式：if (condition) statements;

- 3、除常用方法（如getXxx/isXxx）等外，不要在条件判断中执行其它复杂的语句，将复杂逻辑判断的结果赋值给一个有意义的布尔变量名，以提高可读性。

- 4、循环体中的语句要考量性能，以下操作尽量移至循环体外处理，如定义对象、变量、获取数据库连接，进行不必要的try-catch操作（这个try-catch是否可以移至循环体外）。

- 5、方法中需要进行参数校验的场景：

    -  1）调用频次低的方法。

    - 2） 执行时间开销很大的方法，参数校验时间几乎可以忽略不计，但如果因为参数错误导致中间执行回退，或者错误，那得不偿失。

    - 3） 需要极高稳定性和可用性的方法。

    - 4） 对外提供的开放接口，不管是RPC/API/HTTP接口。

    - 5） 敏感权限入口。

## 1.4   格式规范

- 代码格式化参考 [Google Java编程风格指南中文版](https://www.cnblogs.com/lanxuezaipiao/p/3534447.html)

# 二 、异常规范

## 2.1 异常规范

- 1、不要捕获Java类库中定义的继承自RuntimeException的运行时异常类，如：IndexOutOfBoundsException / NullPointerException，这类异常由程序员预检查来规避，保证程序健壮性。

    - 正例：if(obj != null) {...}

    - 反例：try { obj.method() } catch(NullPointerException e){...};

- 2、异常不要用来做流程控制，条件控制，因为异常的处理效率比条件分支低;

- 3、捕获异常是为了处理它，不要捕获了却什么都不处理而抛弃之，如果不想处理它，请将该异常抛给它的调用者。最外层的业务使用者，必须处理异常，将其转化为用户可以理解的内容;

- 4、有try块放到了事务代码中，catch异常后，如果需要回滚事务，一定要注意手动回滚事务;

- 5、finally块必须对资源对象、流对象进行关闭，有异常也要做try-catch;

- 6、不能在finally块中使用return，finally块中的return返回后方法结束执行，不会再执行try块中的return语句;

- 7、方法的返回值可以为null，不强制返回空集合，或者空对象等，必须添加注释充分说明什么情况下会返回null值。调用方需要进行null判断防止NPE问题。
    - 说明：本规约明确防止NPE是调用者的责任。即使被调用方法返回空集合或者空对象，对调用者来说，也并非高枕无忧，必须考虑到远程调用失败，运行时异常等场景返回null的情况;

- 8、在接口设计中使用“返回错误码”，不要直接抛异常给到调用方;

- 9、定义时区分unchecked / checked 异常，避免直接使用RuntimeException抛出，更不允许抛出Exception或者Throwable，应使用有业务含义的自定义异常。推荐业界已定义过的自定义异常，如：DAOException / ServiceException等;

-  10、避免出现重复的代码（Don’t Repeat Yourself），即DRY原则。 说明：随意复制和粘贴代码，必然会导致代码的重复，在以后需要修改时，需要修改所有的副本，容易遗漏。必要时抽取共性方法，或者抽象公共类，甚至是共用模块;

# 三 、日志规范

## 3.1日志规范

- 1、应用中不要直接使用日志系统（Log4j、Logback）中的API，而应使用common包LogUtils中的API;

- 2、避免重复打印日志，浪费磁盘空间，在log4j.xml中设置additivity=false。
    - 正例：<logger name="com.taobao.dubbo.config" additivity="false">;

- 3、异常信息应该包括两类信息：案发现场信息和异常堆栈信息。如果不处理，则往上抛。
    - 正例：log.error(各类参数或者对象toString + "_" + e.getMessage(), e);

- 4、注意日志输出的级别，error级别只记录系统逻辑出错、异常等重要的错误信息。如非必要，请不要在此场景打出error级别;

- 5、日志级别按debug/info/warn/error四个级别输出，生产环境禁止输出debug日志；有选择地输出info日志；warn级别记录系统逻辑出错、异常等重要的错误信息；error级别只记录致命的、导致流程中止的错误或异常信息;

- 6、debug/info级别的日志输出，必须使用使用占位符的方式，不建议使用条件输出形式。
    - 说明：logger.debug("Processing trade with id: " + id + " symbol: " + symbol); 如果日志级别是warn，上述日志不会打印，但是会执行字符串拼接操作，如果symbol是对象，会执行toString()方法，浪费了系统资源，执行了上述操作，最终日志却没有打印;

# 四、工程规范

## 4.1工程规范

- 1、包名com.hetai.系统名，代码目录结构按以下表格：

| **模块**  | **说明**                                               |
| --------- | ------------------------------------------------------ |
| resource  | 服务资源接口层，REST Resource，定义服务对外的REST 接口 |
| service   | 服务业务层，在service组装业务逻辑                      |
| sao       | 服务远程方法调用层，封装调用其他服务接口               |
| dao       | 服务持久化层，调用DB接口                               |
| vo        | 服务bean对象                                           |
| common    | 服务公共代码，如工具类、常量类等                       |
| mapping   | 调用DB sql配置文件                                     |
| extension | 过滤器、拦截器filter/Interceptor                       |
| handler   | 线程处理实现类，参考《线程池使用指引》                 |



- 2、配置文件目录结构按以下表格：

| **模块**    | **说明**                     |
| ----------- | ---------------------------- |
| dev         | 开发环境配置文件             |
| commit      | 测试环境（api）配置文件      |
| commit_stg2 | 测试环境（api_stg2）配置文件 |
| commit_app  | 测试环境（app）配置文件      |
| formal      | 正式环境配置文件             |
| formal_app  | 正式环境（app）配置文件      |
| common      | 服务公共配置文件             |

# 五 、安全规范

## 5.1 安全规范

- 1、隶属于用户个人的页面或者功能必须进行权限控制校验。 说明：防止没有做水平权限校验就可随意访问、操作别人的数据，比如查看、修改别人的订单;

- 2、用户敏感数据禁止直接展示，必须对展示数据脱敏。 说明：查看个人手机号码会显示成:137****5695，隐藏中间4位，防止隐私泄露;

- 3、用户输入的SQL参数严格使用参数绑定或者METADATA字段值限定，防止SQL注入，禁止字符串拼接SQL访问数据库;

- 4、禁止向HTML页面输出未经安全过滤或未正确转义的用户数据;

- 5、在使用平台资源，譬如短信、邮件、电话、下单、支付，必须实现正确的防重放限制，如数量限制、疲劳度控制、验证码校验，避免被滥刷、资损。 说明：如注册时发送验证码到手机，如果没有限制次数和频率，那么可以利用此功能骚扰到其它用户，并造成短信平台资源浪费;

- 6、发贴、评论、发送即时消息等用户生成内容的场景必须实现防刷、文本内容违禁词过滤等风控策略;

- 7、表单、AJAX提交必须执行CSRF安全过滤。
    - 说明：CSRF(Cross-site request forgery)跨站请求伪造是一类常见编程漏洞。对于存在CSRF漏洞的应用/网站，攻击者可以事先构造好URL，只要受害者用户一访问，后台便在用户不知情情况下对数据库中用户参数进行相应修改;


## git提交规范

### 提交信息格式

<type>(<scope>): <subject>

#### type



| 类型     | 描述                                         |
| -------- | -------------------------------------------- |
| feat     | 新功能                                       |
| fix      | 修复bug                                      |
| docs     | 文档                                         |
| style    | 不影响整体功能使用前提修改样式               |
| refactor | 重构(既没有feat也没有fix,纯粹优化使用此类型) |
| test     | 增加测试                                     |
| chore    | 构建构成或辅助工具的变动                     |

#### scope

- 用于说明 `commit` 影响的范围，比如数据层、控制层、视图层等等，视项目不同而不同。

#### subject

是 `commit` 目的的简短描述，不超过50个字符。

1.以动词开头，使用第一人称现在时，比如change，而不是changed或changes
2.第一个字母小写
3.结尾不加句号（.）


# 类型规范

  - 在使用tkMapper或者mybatisPlus等框架使用，在实体中的属性不要使用java中的8大基本类型。因为基本类型都是有默认值的
  - 而通用框架或者我们自己在xml中根据字段判断的时候都是判断是否为null或者判断是否为空的。如果使用了基本类型那么就会因为默认值导致一些奇怪的bug

